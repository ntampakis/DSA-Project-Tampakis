// ********************************************************
// Interface ListInterface for the ADT list.
// *********************************************************
public interface ListInterface
{
	public boolean isEmpty();
	public int size();
	public Object get(int index) 
					  throws ListIndexOutOfBoundsException;
	public void removeAll();
	public void insert(Object newDataItem);
	public void add(int index, Object newDataItem) 
					throws ListIndexOutOfBoundsException,
						   ListException;
	public void append(Object newDataItem);
	public Object showFront();
	public Object showLast();
	public void remove(int index) 
					   throws ListIndexOutOfBoundsException;
	public boolean exists(Object newDataItem);
}
