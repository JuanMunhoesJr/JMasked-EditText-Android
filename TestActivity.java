package sse.sebraeemsuaempresa.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import sse.sebraeemsuaempresa.R;
import sse.sebraeemsuaempresa.mascara.JMaskedCep;
import sse.sebraeemsuaempresa.mascara.JMaskedCnpj;
import sse.sebraeemsuaempresa.mascara.JMaskedCpf;
import sse.sebraeemsuaempresa.mascara.JMaskedPhone;

/**
 * Created by juanmunhoesjunior on 1/31/17.
 */
public class TestActivity extends AppCompatActivity {

    private EditText etSample;
    private EditText etSample2;
    private EditText etSample3;
    private EditText etSample4;

    private JMaskedPhone phoneMask = null;
    private JMaskedCep cepMask = null;
    private JMaskedCnpj cnpjMask = null;
    private JMaskedCpf cpfMask = null;

    private String mHint = "_";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_activity);

        etSample = (EditText)findViewById(R.id.etSample);
        etSample2 = (EditText)findViewById(R.id.etSample2);
        etSample3 = (EditText)findViewById(R.id.etSample3);
        etSample4 = (EditText)findViewById(R.id.etSample4);

        phoneMask = new JMaskedPhone(etSample,mHint);
        phoneMask.mask();

        cepMask = new JMaskedCep(etSample2,mHint);
        cepMask.mask();

        cnpjMask = new JMaskedCnpj(etSample3,mHint);
        cnpjMask.mask();

        cpfMask = new JMaskedCpf(etSample4,mHint);
        cpfMask.mask();
    }
}
