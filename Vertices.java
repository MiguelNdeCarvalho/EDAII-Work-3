import java.util.List;
import java.util.ArrayList;

public class Vertices
{
	int sala;
	int distancia;
	Vertices predecessor;

	public void setValor(int sala, int distancia)
	{
		this.sala = sala;
		this.distancia = distancia;
		this.predecessor = null;
	}
}