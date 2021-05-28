import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Maze
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException
	{
		//declarar um buffer para puder ler inputs
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

		String[] fistLine = buffer.readLine().split(" ");

		int nRooms = Integer.parseInt(fistLine[0]);

		int nCorridors = Integer.parseInt(fistLine[1]);

		if (nRooms < 2 || nRooms > 5000) return;

		if (nCorridors < 1 || nCorridors > 50000) return;

		int [][] antecessor = new int[nRooms][nRooms];
		int [][] shorterPath = new int[nRooms][nRooms];

		for (int i = 0; i < nRooms; i++)
		{
			for (int j = 0; j < nRooms; j++)
			{
				if (i == j)
					shorterPath[i][j] = 0;

				else
					shorterPath[i][j] = Integer.MAX_VALUE;

				antecessor[i][j] = -1;
			}
		}

		for (int k = 0; k < nCorridors; k++)
		{
			String[] corridorsInf = buffer.readLine().split(" ");

			int i = Integer.parseInt(corridorsInf[0]);

			int j = Integer.parseInt(corridorsInf[1]);

			String crocadileBag = corridorsInf[2];

			int cost;
			if (crocadileBag.compareTo("C") == 0)
				cost = -(Integer.parseInt(corridorsInf[3]));

			else 
				cost = Integer.parseInt(corridorsInf[3]);

			antecessor[i][j] = i;
			shorterPath[i][j] = cost;
		}

		/*for (int i = 0; i < nRooms; i++)
		{
			System.out.println(Arrays.toString(antecessor[i]));
		}

		System.out.println();

		for (int i = 0; i < nRooms; i++)
		{
			System.out.println(Arrays.toString(shorterPath[i]));
		}
		System.out.println();
		System.out.println();*/

		for (int k = 1; k < nRooms; k++)
		{
			//System.out.println("k = " + k);
			for (int i = 0; i < nRooms; i++)
			{
				for (int j = 0; j < nRooms; j++)
				{
					int verifica;

					if(shorterPath[i][k] == Integer.MAX_VALUE || shorterPath[k][j] == Integer.MAX_VALUE)
						verifica = Integer.MAX_VALUE;

					else
						verifica = shorterPath[i][k] + shorterPath[k][j];

					//System.out.println(shorterPath[i][k] + " + " + shorterPath[k][j] +  " = " + verifica);
					if (shorterPath[i][j] > verifica && shorterPath[i][j] > 0)
					{
						//System.out.println("i = " + i + " j = " + j);
						shorterPath[i][j] = verifica;
						antecessor[i][j] = antecessor[i][k];
					}
				}
			}
			for (int i = 0; i < nRooms; i++)
			{
				System.out.println(Arrays.toString(antecessor[i]));
			}

			System.out.println();

			for (int i = 0; i < nRooms; i++)
			{
				System.out.println(Arrays.toString(shorterPath[i]));
			}
			System.out.println();
			System.out.println();
		}
		System.out.println(shorterPath[0][shorterPath[0].length -1] < 0? "yes" : "no");
	}
}