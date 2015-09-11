package nl.tudelft.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nl.tudelft.model.Weapon;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.semgroup4.Resources;


public class WeaponTest extends TestCase {
	
	public WeaponTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(WeaponTest.class);
	}
	
//	public void testGetPlayer() {
//		Player player = new Player(1, 1, null, true);
//		assertNotNull(player);
//	}
	
	public void testGetType() {
		Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
		assertEquals(weapon.getType(), Pickup.WeaponType.REGULAR);
		assertFalse(weapon.getType().equals(Pickup.WeaponType.FLOWER));
	}
	
	public void testGetMaxCount() {
		Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
		Weapon weapon2 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.FLOWER);
		Weapon weapon3 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.DOUBLE);
		Weapon weapon4 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.STICKY);
		assertEquals(weapon.getMaxCount(), 1);
		assertEquals(weapon2.getMaxCount(), 10);
		assertEquals(weapon3.getMaxCount(), 2);
		assertEquals(weapon4.getMaxCount(), 1);
	}
	
	public void testIsSticky() {
		Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.DOUBLE);
		Weapon weapon1 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.STICKY);
		assertFalse(weapon.isSticky());
		assertTrue(weapon1.isSticky());
	}
	
	public void testFire() {
		Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
		assertEquals(weapon.getNumberOfProjectiles(), 0);
		assertEquals(weapon.getMaxCount(), 1);
		weapon.fire(null, 0, 0, 0, 0);
	}
}
