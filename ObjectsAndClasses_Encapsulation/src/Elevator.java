public class Elevator {
    private int currentFloor;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        currentFloor = 1;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        currentFloor = currentFloor > minFloor ? currentFloor - 1 : currentFloor;
        return;
    }

    public void moveUp() {
        currentFloor = currentFloor < maxFloor ? currentFloor + 1 : currentFloor;
        return;
    }


    public void move(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            System.out.println("Неверно задан этаж");
            return;
        }

        if (floor < currentFloor) {
            for (int i = currentFloor; i > floor; i--) {
                moveDown();
                System.out.println("Текущий этаж: " + currentFloor);
            }
        } else if (floor > currentFloor) {
            for (int i = currentFloor; i < floor; i++) {
                moveUp();
                System.out.println("Текущий этаж: " + currentFloor);
            }
        }

        return;
    }
}

