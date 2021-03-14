package main.game.entities.controllables;

public enum UnitStat {
	
	HP(0),
	HP_MAX(1),
	ACCELERATION(2),
	PHYS_DEF(3),
	MAG_DEF(4),
	ATTACK(5),
	ATTACK_RANGE(6);

	private final int val;
	
	private UnitStat(int v) {
		this.val = v;
	}
	
	public int val() {
		return val;
	}
}