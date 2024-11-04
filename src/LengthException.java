public class LengthException extends Exception{
    public LengthException(String pMsg){
        super(pMsg);
    }

    @Override
    public String getMessage(){
        return "Error de length: "+super.getMessage();
    }
}
