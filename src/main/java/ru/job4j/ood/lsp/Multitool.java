package ru.job4j.ood.lsp;

/**
 * Нарушены принципы LSP, т.к.
 * в подклассе ослаблены постусловия.
 */
public class Multitool {
    public static int functions;

    public Multitool(int functions) {
        this.functions = functions;
    }

    public int countFunc() {
        if (functions < 2) {
            throw new IllegalArgumentException("Not a multitool!");
        }
        return functions;
    }

    static class Knife extends Multitool {

        public Knife(int functions) {
            super(functions);
        }

        @Override
        public int countFunc() {
            return functions;
        }
    }

    public static void main(String[] args) {
        Multitool buck = new Knife(1);
        System.out.println(buck.countFunc());
    }
}
