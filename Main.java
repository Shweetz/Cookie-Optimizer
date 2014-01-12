//import java.util.List;
//import java.util.ArrayList;
//import java.util.Iterator;


public class Main {

	public static void main(String[] args) {

		// Mettre dans le premier paramètre le nombre acheté (si au moins 1 acheté)
		
		Objects listObjects[] = { // Initialisation des objets
			new Objects("grandma", 0, 100, 0.5),
			new Objects("farm", 0, 500, 4),
			new Objects("factory", 0, 3000, 10),
			new Objects("mine", 0, 10000, 40),
			new Objects("shipment", 0, 40000, 100),
			new Objects("alchemy lab", 0, 200000, 400),
			new Objects("portal", 0, 1666666, 6666),
			new Objects("time machine", 0, 123456789, 98765),
			new Objects("antimatter condenser", 0, 4000000000L, 999999)
		};
		Objects cursor = new Objects("cursor", 0, 15, 0.1);
		Objects optimal = new Objects("optimal", 0, 1, 0);

		// Mettre dans le dernier paramètre -1 si l'upgrade est acheté
		
		Upgrades listUpgrades[] = { // Initialisation des upgrades
			new Upgrades("Reinforced index finger", 100, 0),
			new Upgrades("Carpal tunnel prevention cream", 400, 0),
			new Upgrades("Ambidextrous", 10000, 0),
			new Upgrades("Forwards from grandma", 1000, 0),
			new Upgrades("Steel-plated rolling pins", 10000, 0),
			new Upgrades("Cheap hoes", 5000, 0),
			new Upgrades("Fertilizer", 50000, 0),
			new Upgrades("Sturdier conveyor belts", 30000, 0),
			new Upgrades("Sugar gas", 100000, 0)
		};
		Upgrades uptimal = new Upgrades("uptimal", 1, 0);
		
		boolean newGame = true;
		boolean upgrade;
		int nombrePossedes = 0;
		double cpstotal = 0;
		
		while (nombrePossedes<cursor.number) // Nombre initial de curseurs
		{
			nombrePossedes++;
			cursor.price*=1.15;
			cpstotal+=cursor.cps;
			newGame = false;
		}
		nombrePossedes = 0;
		for (Objects o : listObjects) // Nombres initiaux des autres objets
		{
			while (nombrePossedes<o.number)
			{
				nombrePossedes++;
				o.price*=1.15;
				cpstotal+=o.cps;
				newGame = false;
			}
			nombrePossedes = 0;
		}	
		
		int startindex = 0;
		if (newGame) // Vérifie si le jeu commence bien à 0
		{
			while (startindex < listUpgrades.length)
			{
				if (listUpgrades[startindex].cps ==-1)
				{
					newGame = false;
					startindex = listUpgrades.length-1;
				}
			startindex++;
			}
		}
		
		String initnew = "In the order, you need to buy : \n";
		
		if (newGame)
		{
			initnew += "The 1st cursor, the next now costs 17 cookies. CPS total = 0.1";
			System.out.println("You didn't buy anything yet.\n\n" + initnew);
			cursor.number++;
			cursor.price*=1.15;
			cpstotal+=cursor.cps;
		}
		else
		{
			String init = "You already have : \n";
			
			if (cursor.number!=0)
			{
				init += (int)cursor.number + " " + cursor.name;
				if (cursor.number!=1)
					init += "s";
				init += "\n";
			}
				
			for (Objects o : listObjects)
			{
				if (o.number!=0)
				{
					init += (int)o.number + " " + o.name;
					if (o.number!=1)
						init += "s";
					init += "\n";
				}
			}
			
			for (Upgrades u : listUpgrades)
			{
				if (u.cps ==-1)
				{
					init += "The \"" + u.name + "\" upgrade";
					init += "\n";
				}
			}
			System.out.print(init + "\n" + initnew);
		}
		
		for (int i=0; i<100; i++) // Nombre d'achats à faire
		{
			for (int j=0; j<listUpgrades.length; j++)
			{
				if (listUpgrades[j].cps!=-1)
				{
					switch (j) {
						case 0: listUpgrades[j].cps=cursor.number/10;
						break;
						case 1: listUpgrades[j].cps=0;
						break;
						case 2: listUpgrades[j].cps=0;
						break;
						case 3: listUpgrades[j].cps=0;
						break;
						case 4: listUpgrades[j].cps=0;
						break;
						case 5: listUpgrades[j].cps=0;
						break;
						case 6: listUpgrades[j].cps=0;
						break;
						case 7: listUpgrades[j].cps=0;
						break;
						case 8: listUpgrades[j].cps=0;
						break;
						default: System.out.println("Upgrade non répertoriée");
						break;
					}
				}
			}
			
			
			upgrade = false;
			
			optimal = cursor;
			
			for (Objects o : listObjects) // On cherche l'objet optimal
			{
				if ((o.price*(o.cps + cpstotal)/o.cps) < (optimal.price*(optimal.cps + cpstotal)/optimal.cps))
					optimal = o;
			}
			
			for (Upgrades u : listUpgrades) // On cherche l'upgrade optimal s'il est mieux que l'objet optimal
			{
				if (u.cps !=-1)
				{
					if (!upgrade)
					{
						if ((u.price*(u.cps + cpstotal)/u.cps) < (optimal.price*(optimal.cps + cpstotal)/optimal.cps))
						{
							uptimal = u;
							upgrade = true;
						}
					}
					else
					{
						if ((u.price*(u.cps + cpstotal)/u.cps) < (uptimal.price*(uptimal.cps + cpstotal)/uptimal.cps))
							uptimal = u;
					}
				}
			}
			
			if (!upgrade)
			{
				Objects o = cursor;
				if (optimal.name != "cursor")
				{	
					int index = 0;
					while (listObjects[index].name != optimal.name) 
					{
						index++;
						if (index == listObjects.length) 
							System.err.println("Optimal doesn't have a correct name.");
					} 
					o = listObjects[index];
				}

				o.number++;
				o.price*=1.15;
				cpstotal+=o.cps;
				
				String stg = "The " + (int)optimal.number;
				
				if (optimal.number%10==1 && optimal.number!=11)
					stg += "st ";		
				else if (optimal.number%10==2 && optimal.number!=12)
					stg += "nd ";			
				else if (optimal.number%10==3 && optimal.number!=13)
					stg += "rd ";			
				else 
					stg += "th ";		
				
				stg += optimal.name + ", the next now costs " + String.format("%.0f", optimal.price) + " cookies, ";
				
				stg += "CPS total = " + String.format("%.1f",  cpstotal);
				//stg += String.format("\nCoucou arg=%d,  arg2=%3d, arg3=%6.2f, arg4=%04X", 1, 2, 3.4, 17);
				System.out.println(stg);
			}
			else
			{
				int index = 0;
				while (listUpgrades[index].name != uptimal.name) 
				{
					index++;
					if (index == listUpgrades.length) 
						System.err.println("Uptimal doesn't have a correct name.");
				} 
				cpstotal += listUpgrades[index].cps;
				listUpgrades[index].cps=-1;
				
				switch (index) {
				case 0: cursor.cps+=0.1;
				break;
				case 1: listUpgrades[index].cps=0;
				break;
				case 2: listUpgrades[index].cps=0;
				break;
				case 3: listUpgrades[index].cps=0;
				break;
				case 4: listUpgrades[index].cps=0;
				break;
				case 5: listUpgrades[index].cps=0;
				break;
				case 6: listUpgrades[index].cps=0;
				break;
				case 7: listUpgrades[index].cps=0;
				break;
				case 8: listUpgrades[index].cps=0;
				break;
				default: System.out.println("Upgrade non répertoriée 2");
				break;
			}
				
				System.out.println("The \"" + listUpgrades[index].name + "\" upgrade, CPS total = " + cpstotal);
				
			}
		} // Fin de l'achat en cours
	} // Fin du main
}
