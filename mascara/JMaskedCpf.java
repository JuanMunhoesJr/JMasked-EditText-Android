package sse.sebraeemsuaempresa.mascara;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Created by juanmunhoesjunior on 1/31/17.
 */
public class JMaskedCpf extends JChieldMasked{


    public JMaskedCpf(EditText edt, String hint){
        super(edt,hint);
    }

    @Override
    public void mask() {
        if(editText != null){

            // Setar template padrão
            // 83.718.195/0001-37
            editText.setText("@@@.@@@.@@@-@@".replace("@",hint));

            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.setSelection(getFirstFreePosition(editText, hint));
                }
            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    beforeTextChange = s.toString();

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    editText.removeTextChangedListener(this);

                    String last = "";
                    if(start > 0 && start < beforeTextChange.length())
                        last = String.valueOf(beforeTextChange.charAt(start));

                    // Checar se for apagar e pos = 0 impedir:
                    if(start == 0 && before == 1){
                        editText.setText("@@@.@@@.@@@-@@".replace("@",hint));
                        editText.setSelection(getFirstFreePosition(editText,hint));
                        editText.addTextChangedListener(this);
                        return;
                    }
                    else if(before == 1 && (last.equals("-") || last.equals("."))){
                        beforeTextChange = beforeTextChange.substring(0,start - 1);
                        String numeros = beforeTextChange.toString().replace("-","").replace(".","").replace(hint,"");
                        // format
                        String formatted = generateMask(numeros);
                        editText.setText(formatted);
                        editText.setSelection(getFirstFreePosition(editText,hint));
                        editText.addTextChangedListener(this);
                        return;
                    }
                    else{
                        // Remover a mascara (deixar apenas numeros)
                        String numeros = s.toString().replace("-","").replace(".","").replace(hint,"");

                        // Verificar se a ultima insercao eh valida
                        // 333.444.555-12

                        if(numeros.length() > 11){
                            String novo = numeros.substring(0,10);
                            editText.setText(novo);
                            editText.setSelection(getFirstFreePosition(editText,hint));
                            editText.addTextChangedListener(this);
                            return;
                        }
                        else{
                            String formatted = generateMask(numeros);
                            editText.setText(formatted);
                            editText.setSelection(getFirstFreePosition(editText,hint));
                            editText.addTextChangedListener(this);
                            return;
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    @Override
    public String generateMask(String unmasked) {
        String formatted = "";
        int currentPosition = 0;
        for(int i =0;i < 14;i++){
            // 333.444.555-12
            if(i == 3 || i == 7)
                formatted += ".";

            else if(i == 11)
                formatted += "-";

            else if(currentPosition < unmasked.length()){
                formatted += String.valueOf(unmasked.charAt(currentPosition));
                currentPosition++;
            }
            else
                formatted += hint;
        }
        return formatted;
    }
}
