package ru.job4j.ood.lsp;

/**
 * Нарушен принцип LSP, т.к.
 * в подклассе усилены
 * предусловия.
 */
public class Weapon {
    protected int ammo;

    public Weapon (int ammo) {
        this.ammo = ammo;
    }

    public void shoot() {
        if (ammo < 1) {
            throw new IllegalArgumentException("Not enough ammo!");
        }
            System.out.println("BOOM!!!");
        }


    private static class MachineGun extends Weapon {

        public MachineGun(int ammo) {
            super(ammo);
        }

        public void shoot() {
            if (ammo < 200) {
                throw new IllegalArgumentException("Not enough ammo!");
            }
            System.out.println("BOOM!!!");
        }
    }

    public static void main(String[] args) {
        Weapon rpk = new MachineGun(5);
        rpk.shoot();
    }

}
