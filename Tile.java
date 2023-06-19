public class Tile
{
    private boolean isMine;
    private boolean showNumber;
    private boolean changed;
    
    public Tile(boolean mine)
    {
        isMine = mine;
        showNumber = false;
        changed = false;
    }
    public boolean getMine()
    {
        return isMine;
    }
    public boolean getChanged()
    {
        return changed;
    }
    public boolean getShow()
    {
        return showNumber;
    }
    public void setMine()
    {
        isMine = true;
    }
    public void setShow()
    {
        showNumber = true;
    }
    public void setChanged()
    {
        changed = true;
    }
}