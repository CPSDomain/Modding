package com.robvanblerk.tieredpower.stargate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;

/**
 * Open connections between two gates (each gate known by its ring's bottom-middle block), and the Gate Interfaces set
 * to receive at each gate. Server-side and in memory only: an open gate closes when the world is reloaded anyway.
 */
@net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = com.robvanblerk.tieredpower.TieredPower.MOD_ID)
public final class Wormholes {
	private static final Map<GlobalPos, GlobalPos> LINKS = new HashMap<>();
	private static final Map<GlobalPos, Set<BlockPos>> RECEIVERS = new HashMap<>();

	private Wormholes() {}

	public static void connect(GlobalPos a, GlobalPos b) {
		LINKS.put(a, b);
		LINKS.put(b, a);
	}

	public static void disconnect(GlobalPos a) {
		GlobalPos b = LINKS.remove(a);
		if (b != null && a.equals(LINKS.get(b))) LINKS.remove(b);
	}

	/** The gate at the other end of an open connection, or null. */
	public static @Nullable GlobalPos other(GlobalPos gate) {
		return LINKS.get(gate);
	}

	public static void addReceiver(GlobalPos gate, BlockPos iface) {
		RECEIVERS.computeIfAbsent(gate, k -> new HashSet<>()).add(iface.immutable());
	}

	public static void removeReceiver(BlockPos iface, String dim) {
		RECEIVERS.entrySet().removeIf(e -> {
			if (e.getKey().dimension().location().toString().equals(dim)) e.getValue().remove(iface);
			return e.getValue().isEmpty();
		});
	}

	public static Set<BlockPos> receivers(GlobalPos gate) {
		return RECEIVERS.getOrDefault(gate, Set.of());
	}

	@net.minecraftforge.eventbus.api.SubscribeEvent
	public static void onServerStopped(net.minecraftforge.event.server.ServerStoppedEvent event) {
		clear();
	}

	public static void clear() {
		LINKS.clear();
		RECEIVERS.clear();
	}
}
