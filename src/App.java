import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            PolySportsDatabase mydatabase = PolySportsDatabase.getInstance();
            SportsDAO sportsDAO = new SportsDAO(mydatabase);
            ArrayList<Sport> results = new ArrayList<Sport>();
            Scanner scanner = new Scanner(System.in);
            int option = 1;
            do{
                System.out.println("\n\n########## Menu ##########\n");
                System.out.println("1. Afficher tous les sports");
                System.out.println("2. Trouver un sport par id");
                System.out.println("3. Trouver un sport par nom");
                System.out.println("4. Créer un sport");
                System.out.println("5. Modifier un sport");
                System.out.println("6. Supprimer un sport");
                System.out.println("0. Quitter");
                option = scanner.nextInt();
                switch(option){
                    case 1:
                        System.out.println("\n########## Afficher tous les sports ##########\n");
                        results = sportsDAO.findAll();
                        for(Sport s : results){
                            System.out.println(s);
                        }
                        break;
                    case 2:
                        System.out.println("\n########## Trouver un sport par id ##########\n");
                        System.out.print("Entrez l'id du sport recherché: ");
                        int id = scanner.nextInt();
                        Sport sport = sportsDAO.findById(id);
                        System.out.println("Sport with id "+ id +" : " + sport);
                        break;
                    case 3:
                        System.out.println("\n########## Trouver un sport par nom ##########\n");
                        System.out.print("Entrez le nom du sport recherché: ");
                        String name = scanner.next();
                        results = sportsDAO.findByName(name);
                        System.out.println("Sports with name "+ name +" : ");
                        for(Sport s : results){
                            System.out.println(s);
                        }
                        break;
                    case 4:
                        System.out.println("\n########## Créer un sport ##########\n");
                        System.out.print("Entrez le nom du sport: ");
                        String sportName = scanner.next();
                        System.out.print("Entrez le nombre de participants: ");
                        int nbParticipants = scanner.nextInt();
                        Sport newSport = new Sport(0, sportName, nbParticipants);
                        if(sportsDAO.insert(newSport)){
                            System.out.println("Sport ajouté avec succès");
                        }else{
                            System.out.println("Erreur lors de l'ajout du sport");
                        }
                        break;
                    case 5:
                        System.out.println("\n########## Modifier un sport ##########\n");
                        System.out.print("Entrez l'id du sport à modifier: ");
                        int idSport = scanner.nextInt();
                        System.out.print("Entrez le nouveau nom du sport: ");
                        String newSportName = scanner.next();
                        System.out.print("Entrez le nouveau nombre de participants: ");
                        int newNbParticipants = scanner.nextInt();
                        Sport updatedSport = new Sport(idSport, newSportName, newNbParticipants);
                        if(sportsDAO.update(idSport, updatedSport)){
                            System.out.println("Sport modifié avec succès");
                        }else{
                            System.out.println("Erreur lors de la modification du sport");
                        }
                        break;
                    case 6:
                        System.out.println("\n########## Supprimer un sport ##########\n");
                        System.out.print("Entrez l'id du sport à supprimer: ");
                        int idSportToDelete = scanner.nextInt();
                        if(sportsDAO.delete(idSportToDelete)){
                            System.out.println("Sport supprimé avec succès");
                        }else{
                            System.out.println("Erreur lors de la suppression du sport");
                        }
                        break;
                    case 0:
                        System.out.println("\nFin du programme\n");
                        break;
                    default:
                        System.out.println("Option invalide");
                        break;
                }
            }while(option != 0);

            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
