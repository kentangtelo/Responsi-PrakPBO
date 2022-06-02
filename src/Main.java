
import Controller.Controller;
import Model.Model;
import View.MovieView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TUF
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MovieView view = new MovieView();
        Model model = new Model();
        Controller controller = new Controller(model, view);
    }
    
}
