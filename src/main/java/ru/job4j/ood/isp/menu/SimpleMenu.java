package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName,
                       ActionDelegate actionDelegate) {
        if (Objects.equals(parentName, ROOT)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            return true;
        }
        Optional<ItemInfo> rootParent = findItem(parentName);
        if (rootParent.isPresent()) {
            rootParent.get().menuItem.getChildren()
                    .add(new SimpleMenuItem(childName, actionDelegate));
            return true;
        }
        return false;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> rsl = Optional.empty();
        Optional<ItemInfo> itemOpt = findItem(itemName);
        if (itemOpt.isPresent()) {
            rsl = Optional.of(new MenuItemInfo(itemOpt.get().menuItem,
                    itemOpt.get().number));
        }
        return rsl;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator it = new DFSIterator();

        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                boolean rsl = false;
                if (it.hasNext()) {
                    rsl = true;
                }
                return rsl;
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ItemInfo itemInfo = it.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
      DFSIterator it = new DFSIterator();
      while (it.hasNext()) {
          ItemInfo itemInfo = it.next();
          if (name.equals(itemInfo.menuItem.getName())) {
              return Optional.ofNullable(itemInfo);
          }
      }
      return Optional.empty();
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {
        private Deque<MenuItem> stack = new LinkedList<>();
        private Deque<String> numbers = new LinkedList<>();

        public Deque<MenuItem> getStack() {
            return stack;
        }

        public void setStack(Deque<MenuItem> stack) {
            this.stack = stack;
        }

        public Deque<String> getNumbers() {
            return numbers;
        }

        public void setNumbers(Deque<String> numbers) {
            this.numbers = numbers;
        }

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {
        private MenuItem menuItem;
        private String number;

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public void setMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
