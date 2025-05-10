public class MVCMain {
    public static void main(String[] args) {
        Chess chess = new Chess();
        View view = new View();
        Controller controller = new Controller(chess, view);
        controller.updateView();
    }
}