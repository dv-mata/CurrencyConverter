import models.ClientRequest;
import models.CurrencyCode;

import java.io.IOException;

import java.util.Scanner;

import static models.CurrencyLogFile.ReadLogFile;
import static models.CurrencyLogFile.writeToFile;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {

        ClientRequest cr = new ClientRequest();

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while(!exit) {
            try {
                System.out.println("Bienvenido a la Conversora de Monedas");
                System.out.println("=====================================");
                System.out.println("Selecciona una opción:");
                System.out.println("1. Calculadora de conversión");
                System.out.println("2. Ver registro de ultimas conversiones");
                System.out.println("3. Salir");
                System.out.println("=====================================");
                System.out.println(" ");
                int userInput = Integer.parseInt(sc.next());

                switch (userInput) {
                    case 1:
                        while(true) {
                            String currency;
                            int amount;
                            String targetCurrency;

                            while(true) {
                                try{
                                    System.out.println("Ingresa el código del tipo de moneda a convertir: Ej: USD");
                                    currency = sc.next().toUpperCase();

                                    CurrencyCode.valueOf(currency);
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("El código introducido no es válido");
                                }
                            }

                            while(true) {
                                try{
                                    System.out.println("Ingresa la cantidad a convertir: ");
                                    amount = Integer.parseInt(sc.next());
                                    if (amount > 0) {
                                        break;
                                    } else{
                                        System.out.println("El valor introducido no es un número positivo");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido no es un número");
                                }
                            }

                            while(true) {
                                try{
                                    System.out.println("Ingresa el código del pais del tipo de moneda a convertir: Ej: MXN");
                                    targetCurrency = sc.next().toUpperCase();

                                    CurrencyCode.valueOf(targetCurrency);
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("El código introducido no es válido");
                                }
                            }

                            String result = cr.getExchangeRate(currency, targetCurrency, amount);
                            writeToFile(result);

                            System.out.println(result);
                            break;
                        }
                        System.out.println(" ");
                        break;
                    case 2:
                        ReadLogFile();
                        System.out.println(" ");
                        break;
                    case 3:
                        System.out.println("Gracias por usar la Conversora de Monedas");
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción no válida");
                }


            } catch (NumberFormatException e) {
                System.out.println("El valor introducido no es un número");
            }
        }




    }
}