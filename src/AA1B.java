import java.util.Scanner;

/**
 * @author borjaMontseny
 */
public class AA1B {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("IBC MAGIC 2022\n");

		// variables on s'anota Atac i Defensa
		double puntsAtac = 0;
		double puntsDefensa = 0;

		// contadors
		int contadorCartes = 0;
		double contadorOfensa = 0;
		double contadorDefensa = 0;
		double contadorNeutral = 0;
		double totalAtac = 0;
		double totalDefensa = 0;

		// mitjana dels punts d’atac de les cartes ofensives.
		double totalAtacOfensiu = 0;

		// mitjana dels punts d’atac de les cartes neutres.
		double totalAtacNeutre = 0;

		// mitjana dels punts de defensa de les cartes defensives.
		double totalDefensaDefensiva = 0;

		// missatges per al jugador
		String msgDefinirAtac = "Punts d'Atac carta ";
		String msgDefinirDefensa = "Punts de Defensa carta ";
		String msgAtacError = "No es permeten cartes amb punts d'atac menors a 1 o majors de 5!\n";
		String msgDefensaError = "No es permeten cartes amb punts defensa menors a 1 o majors de 5!\n";
		String msgDiferenciaAtDef = "La diferència entre Atac i Defensa no pot ser superior a 3.5!\n";
		String msgCapCarta = "No hi ha cap carta";
		String msgUnaCarta = "Amb una sola carta no es poden fer mitjes";

		// anunciadors de les mitjanes
		String mitjanaAtacOfensives = "Punts d’Atac de les Cartes Ofensives: ";
		String mitjanaAtacNeutres = "Punts d’Atac de les Cartes Neutres: ";
		String mitjanaDefensaDefensives = "Punts de Defensa de les Cartes Defensives: ";

		// anunciadors de les mitjanes totals
		String mitjanaAtac = "Punts d'Atac: ";
		String mitjanaDefensa = "Punts de Defensa: ";

		// anunciadors dels percentatges
		String percentatgeOfensives = "Cartes Ofensives: ";
		String percentatgeNeutres = "Cartes Neutres: ";
		String percentatgeDefensives = "Cartes Defensives: ";

		// inicialitzem l'Escàner
		Scanner sc = new Scanner(System.in);

		while ((puntsAtac != -1) || (contadorCartes < 2)) {

			// demanem punts d'atac
			System.out.print(msgDefinirAtac + (contadorCartes + 1) + ": ");
			puntsAtac = sc.nextDouble();

			// boolean per verificar el rang (PA >= 1 && PA <= 5)
			boolean atacValid = ((puntsAtac >= 1) && (puntsAtac <= 5)) && (puntsAtac != -1);

			// mentres l'atac no sigui valid o sigui -1, el seguirem demanant
			while (!atacValid && puntsAtac != -1) {
				System.out.println(msgAtacError);
				System.out.print(msgDefinirAtac + (contadorCartes + 1) + ": ");
				puntsAtac = sc.nextDouble();
				atacValid = ((puntsAtac >= 1) && (puntsAtac <= 5)) && (puntsAtac != -1);
			}

			// if atac 1-5
			if (atacValid) {
				System.out.print(msgDefinirDefensa + (contadorCartes + 1) + ": ");
				puntsDefensa = sc.nextDouble();

				boolean defensaValid = ((puntsDefensa >= 0) && (puntsDefensa <= 5)) && (puntsDefensa != -1);

				// boolean per fer més llegible la comprovacio de 3,5 de diferència
				boolean diferenciaAtDefOK = ((puntsDefensa >= 0) && (puntsDefensa <= 5))
						&& (Math.abs(puntsAtac - puntsDefensa) < 3.5);

				// bucle per si defensa no es entre 0 5 o diferencia > 3.5
				while (!defensaValid || !diferenciaAtDefOK) {

					if (!defensaValid) {
						System.out.println(msgDefensaError);

					} else if (!diferenciaAtDefOK) {
						System.out.println(msgDiferenciaAtDef);
					}

					System.out.print(msgDefinirDefensa + (contadorCartes + 1) + ": ");
					puntsDefensa = sc.nextDouble();

					defensaValid = ((puntsDefensa >= 0) && (puntsDefensa <= 5)) && (puntsDefensa != -1);

					diferenciaAtDefOK = ((puntsDefensa >= 0) && (puntsDefensa <= 5))
							&& (Math.abs(puntsAtac - puntsDefensa) < 3.5);

				}

				// comprovem si la carta es válida per a pasar a la següent
				if (atacValid && defensaValid) {
					// sumem la carta al total de cartes
					contadorCartes++;
					// fem les sumes a les variables que farán les mitjes
					totalAtac += puntsAtac;
					totalDefensa += puntsDefensa;
				}

				// categoría de la carta + afegir-la al contador per categoria
				if (puntsAtac >= 3.8) {
					System.out.println("CARTA OFENSIVA\n");
					contadorOfensa++;
					totalAtacOfensiu += puntsAtac;
				} else if ((puntsAtac >= 2.5 && puntsAtac <= 3.5) && (puntsDefensa >= 2.5 && puntsDefensa <= 3.5)) {
					System.out.println("CARTA NEUTRA\n");
					contadorNeutral++;
					totalAtacNeutre += puntsAtac;
				} else if (puntsDefensa >= 4) {
					System.out.println("CARTA DEFENSIVA\n");
					contadorDefensa++;
					totalDefensaDefensiva += puntsDefensa;
				} else {
					System.out.println("CARTA NEUTRA\n");
					contadorNeutral++;
					totalAtacNeutre += puntsAtac;
				}

			}

			if (contadorCartes == 0 && puntsAtac == -1) {
				System.out.println(msgCapCarta + "\n");
			} else if (contadorCartes == 1 && puntsAtac == -1) {
				System.out.println(msgUnaCarta + "\n");
			}

		}

		sc.close();

		System.out.println("\nMITJANES");
		if (contadorOfensa == 0) {
			System.out.println(mitjanaAtacOfensives + msgCapCarta);
		} else {
			System.out.println(mitjanaAtacOfensives + (totalAtacOfensiu / contadorOfensa) + " punts");
		}
		if (contadorNeutral == 0) {
			System.out.println(mitjanaAtacNeutres + msgCapCarta);
		} else {
			System.out.println(mitjanaAtacNeutres + (totalAtacNeutre / contadorNeutral) + " punts");
		}
		if (contadorDefensa == 0) {
			System.out.println(mitjanaDefensaDefensives + msgCapCarta);
		} else {
			System.out.println(mitjanaDefensaDefensives + (totalDefensaDefensiva / contadorDefensa) + " punts");
		}

		// mostrem les mitjanes totals
		System.out.println("\nMITJANES TOTALS");
		System.out.println(mitjanaAtac + (totalAtac / contadorCartes) + " punts");
		System.out.println(mitjanaDefensa + (totalDefensa / contadorCartes) + " punts");

		// mostrem els percentatges
		System.out.println("\nPERCENTATGES");
		System.out.println(percentatgeOfensives + ((contadorOfensa * 100) / contadorCartes) + "%");
		System.out.println(percentatgeNeutres + ((contadorNeutral * 100) / contadorCartes) + "%");
		System.out.println(percentatgeDefensives + ((contadorDefensa * 100) / contadorCartes) + "%");

	}

}
