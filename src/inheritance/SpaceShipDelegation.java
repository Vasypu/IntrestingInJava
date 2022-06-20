package inheritance;

//делегироваение используется когда класс не может наследоваться от другого класса, но методы его может использовать
class SpaceShipControls {
    void up(int velocity) {}
    void down(int velocity) {}
    void left(int velocity) {}
    void right(int velocity) {}
    void back(int velocity) {}
}

public class SpaceShipDelegation {
    private String name;
    private SpaceShipControls controls = new SpaceShipControls();
    public SpaceShipDelegation(String name) {
        this.name = name;
    }

    public void back(int velocity) {
        controls.back(velocity);
    }
    public void down(int velocity) {
        controls.down(velocity);
    }
    void up(int velocity) {
        controls.up(velocity);
    }
    void left(int velocity) {
        controls.left(velocity);
    }
    void right(int velocity) {
        controls.right(velocity);
    }
}
