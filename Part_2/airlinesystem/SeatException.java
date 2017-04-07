package airlinesystem;

public class SeatException extends Exception {
	public SeatException()
	{
		super("There are no more seats");
	}
}
