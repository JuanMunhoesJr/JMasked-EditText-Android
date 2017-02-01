package mascara;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Created by juanmunhoesjunior on 1/31/17.
 */
public class JMaskedPhone extends JChieldMasked{

    public JMaskedPhone(EditText editText, String hintText){
        super(editText,hintText);
    }

    // Passa o edittext a ser mascarado junto com um texto padrão a ser preenchido
    // Exemplo: (__)____-____ ou (##)#####-#####

    @Override
    public void mask() {
        if(editText != null){

            // Setar template padrão
            editText.setText("(@@)@@@@-@@@@".replace("@",hint));

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
                        editText.setText("(@@)@@@@-@@@@".replace("@",hint));
                        editText.setSelection(getFirstFreePosition(editText,hint));
                        editText.addTextChangedListener(this);
                        return;
                    }
                    else if(before == 1 && (last.equals("-") || last.equals("(") || last.equals(")") || last.equals("_"))){
                        beforeTextChange = beforeTextChange.substring(0,start - 1);
                        String numeros = beforeTextChange.toString().replace("(","").replace(")","").replace("-","").replace(hint,"");
                        // format
                        String formatted = generateMask(numeros);
                        editText.setText(formatted);
                        editText.setSelection(getFirstFreePosition(editText,hint));
                        editText.addTextChangedListener(this);
                        return;
                    }
                    else{
                        // Remover a mascara (deixar apenas numeros)
                        String numeros = s.toString().replace("(","").replace(")","").replace("-","").replace(hint,"");

                        // Verificar se a ultima insercao eh valida
                        //1) verificar se sao 8 ou 9 numeros
                        if(numeros.length() > 2){
                            String teste = String.valueOf(numeros.charAt(2));
                            if(teste.equals("9")){
                                // 9 numeros
                                if(numeros.length() > 12){
                                    String novo = numeros.substring(0,11);
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
                            else{
                                // 8 numeros
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
                        else{
                            // Reformata
                            // reposiciona
                            // inclui novo listener
                            String formatted = generateMask(numeros);
                            editText.setText(formatted);
                            editText.setSelection(getFirstFreePosition(editText,hint));
                            editText.addTextChangedListener(this);
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
    public String generateMask(String unmasked){
        String strNumeros = unmasked;
        String formatted = "";
        if (strNumeros.length() > 2) {

            if (String.valueOf(strNumeros.charAt(2)).equals("9")) {
                //"(12)95434-2312"

                int currentPosition = 0;
                for (int i = 0; i < 14; i++) {
                    if (i == 0)
                        formatted += "(";
                    else if (i == 3)
                        formatted += ")";

                    else if (i == 9)
                        formatted += "-";
                    else if (currentPosition < strNumeros.length()) {
                        formatted += String.valueOf(strNumeros.charAt(currentPosition));
                        currentPosition++;
                    } else
                        formatted += hint;
                }
            } else {
                int currentPosition = 0;
                for (int i = 0; i < 13; i++) {
                    if (i == 0)
                        formatted += "(";
                    else if (i == 3)
                        formatted += ")";

                    else if (i == 8)
                        formatted += "-";

                    else if (currentPosition < strNumeros.length()) {
                        formatted += String.valueOf(strNumeros.charAt(currentPosition));
                        currentPosition++;
                    } else
                        formatted += hint;
                }
            }
        }
        else{
            int currentPosition = 0;
            for (int i = 0; i < 12; i++) {
                if (i == 0)
                    formatted += "(";
                else if (i == 3)
                    formatted += ")";

                else if (i == 8)
                    formatted += "-";

                else if (currentPosition < strNumeros.length()) {
                    formatted += String.valueOf(strNumeros.charAt(currentPosition));
                    currentPosition++;
                } else
                    formatted += hint;
            }
        }
        return formatted;
    }
}
