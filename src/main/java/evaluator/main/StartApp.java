package evaluator.main;

import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.util.InputValidation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//functionalitati
//i.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//ii.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//iii.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

	private static final String file = "intrebari.txt";
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		AppController appController = new AppController();
		
		boolean activ = true;
		String optiune;
		
		while(activ){
			
			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Exit");
			System.out.println("");
			
			optiune = console.readLine();

			appController.loadIntrebariFromFile(file);

			switch(optiune){
			case "1" :
				System.out.println("Enuntul intrebarii: ");
				String enunt = console.readLine();
				System.out.println("Varianta 1: ");

				try {
					String v1 = console.readLine();

					System.out.println("Varianta 2: ");
					String v2 = console.readLine();

					System.out.println("Varianta 3: ");
					String v3 = console.readLine();

					System.out.println("Numarul variantei corecte: ");
					String vRight = console.readLine();

					System.out.println("Domeniul intrebarii: ");
					String domeniu = console.readLine();

					Intrebare intrebare = new Intrebare(enunt, v1, v2, v3,vRight, domeniu);

					appController.addNewIntrebare(intrebare);
					}
					catch (DuplicateIntrebareException exDup)
					{
						System.out.println(exDup.getMessage());
					}

					catch(InputValidationFailedException exDomeniu) {
						System.out.println(exDomeniu.getMessage());
					}
				break;
			case "2" :
				try{
					appController.createNewTest();
					System.out.println("Test creat!");
				}
				catch (NotAbleToCreateTestException textEx)
				{
					System.out.println(textEx.getMessage());
				}
			case "3" :
				Statistica statistica;
				try {
					statistica = appController.getStatistica();
					System.out.println(statistica);
				} catch (NotAbleToCreateStatisticsException e) {
					System.out.println("Nu se poate genera o statistica!");
				}
				
				break;
			case "4" :
				activ = false;
				break;
			default:
				break;
			}
		}
		
	}

}
