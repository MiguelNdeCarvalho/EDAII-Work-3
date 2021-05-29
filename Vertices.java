import java.util.List;
import java.util.ArrayList;

public class Vertices
{
	int sala;
	List<Arcos> adjacentes;
	int distancia;

	public void setValor(int sala, int distancia)
	{
		this.sala = sala;
		this.distancia = distancia;
		this.adjacentes = new ArrayList<>();
	}
}