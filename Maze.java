import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Maze
{
	//definir infinito como o maior numero inteiro de 32 bits
	static int infinity = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException
	{
		//declarar um buffer para puder ler inputs
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

		String[] fistLine = buffer.readLine().split(" ");

		//numero de salas
		int nRooms = Integer.parseInt(fistLine[0]);
		//numero de corredores
		int nCorridors = Integer.parseInt(fistLine[1]);

		//limitacoes do programa
		if (nRooms < 2 || nRooms > 5000) return;
		if (nCorridors < 1 || nCorridors > 50000) return;

		//lista com todas as salas
		List<Vertices> listaSalas = new ArrayList<>();

		//criar uma sala com o numero i e a uma distancia desconhecida (infinito), adicionando cada uma delas a lista de todas as salas
		for (int i = 0; i < nRooms; i++)
		{
			Vertices sala = new Vertices();

			sala.setValor(i, infinity);
			
			listaSalas.add(sala);
		}

		//criar um corredor dando a sala de partida, a sala final e o custo de atravessa-lo
		//caso haja um saco de dinheiro (B), entao adiciona ao seu saldo, caso tenha um crocodilo (C) tera de gastar do seu saldo ou colocar em credito
		//por fim adicionamos aos corredores que iniciam na sala inicial
		for (int i = 0; i < nCorridors; i++)
		{
			Arcos corredor = new Arcos();

			String[] corridorsInf = buffer.readLine().split(" ");

			int cost, salaInicial = Integer.parseInt(corridorsInf[0]), salaFinal = Integer.parseInt(corridorsInf[1]);
			String crocadileBag = corridorsInf[2];

			if (crocadileBag.compareTo("C") == 0)
				cost = -(Integer.parseInt(corridorsInf[3]));
			else 
				cost = Integer.parseInt(corridorsInf[3]);

			corredor.setValor(cost, listaSalas.get(salaInicial), listaSalas.get(salaFinal));

			listaSalas.get(salaInicial).adjacentes.add(corredor);
		}

		//indicar que a sala de partida se encontra a distancia 0
		listaSalas.get(0).distancia = 0;

		/*for (Vertices v : listaSalas)
		{
			System.out.println("\nsala: " + v.sala  + "\ndistancia: " + (v.distancia == 2147483647? "infinito" : v.distancia) + " com os corredors:");
			for (Arcos corredor : v.adjacentes)
			{
				System.out.println("termina em " + corredor.salaFinal.sala + " com peso: " + corredor.peso);
			}
		}*/

		//percorre todas as salas para encontrar o caminho mais curto ate a saida
		for (Vertices sala : listaSalas)
		{
			//calcula se a distancia da sala inicial daquele corredor + o custo de atravesar o corredor é menor que a distancia da sala final
			//caso seja entao atualiza a distanca da sala final
			for (Arcos corredor : sala.adjacentes)
			{
				int distancia = corredor.salaInicial.distancia + corredor.peso;

				if (distancia < corredor.salaFinal.distancia)
					corredor.salaFinal.distancia = distancia;
			}
		}

		//indicador que sinaliza a existencia de um ciclo no labirinto que apresenta um custo negativo (soma total dos caminhos do ciclo)
		boolean flag = false;
		//System.out.println("\n\n");

		//percorrer todas as salas de novo com o mesmo processo para verificar se existe algum ciclo
		for (Vertices sala : listaSalas)
		{
			for (Arcos corredor : sala.adjacentes)
			{
				int distancia = corredor.salaInicial.distancia + corredor.peso;
				//System.out.println("(sala inicial dist) " + corredor.salaInicial.distancia + " + (peso do corredor) " + corredor.peso + " < (sala final dist)" + corredor.salaFinal.distancia + " " + corredor.salaFinal.sala);
				if (corredor.salaInicial.distancia != infinity && distancia < corredor.salaFinal.distancia)
					flag = true;
				//System.out.println(flag);
			}
		}

		/*System.out.println("\n\n");
		for (Vertices v : listaSalas)
		{
			System.out.println("\nsala: " + v.sala  + "\ndistancia: " + (v.distancia == 2147483647? "infinito" : v.distancia) + " com os corredors:");
			for (Arcos corredor : v.adjacentes)
			{
				System.out.println("termina em " + corredor.salaFinal.sala + " com peso: " + corredor.peso);
			}
		}*/
		
		//resposta ao problema
		//caso tenha ciclos negativos ou a distancia da sala de saida seja menor que 0, entao a resposta é "yes", ficou a dever
		//caso nao tenha ciclos negativos e a distancia da sala de saida seja maior ou igual que 0, entao a resposta é "no", nao ficou a dever
		System.out.println((flag || listaSalas.get(listaSalas.size() - 1).distancia < 0)? "yes" : "no");
	}
}