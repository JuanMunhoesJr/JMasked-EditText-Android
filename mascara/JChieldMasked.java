package sse.sebraeemsuaempresa.mascara;

import android.widget.EditText;

/**
 * Created by juanmunhoesjunior on 1/31/17.
 */
public abstract class JChieldMasked {

    protected String beforeTextChange = "";
    protected String hint; // nao utilizar os que sao especiais como . - / ( )
    protected EditText editText;

    public JChieldMasked(EditText editText, String hintText){
        this.editText = editText;
        this.hint = hintText;
    }

    public abstract void mask();

    protected int getFirstFreePosition(EditText editText, String hintText){
        int position = 0;
        boolean found = false;
        if(editText != null){
            for(int i =0;i < editText.getText().toString().length();i++){
                String temp = editText.getText().toString();
                String x = String.valueOf(temp.charAt(i));
                if(x.equals(hintText)){
                    position = i;
                    found = true;
                    break;
                }
            }
            if(!found)
                position = editText.getText().toString().length();
        }
        return position;
    }

    public abstract String generateMask(String unmasked);
}
