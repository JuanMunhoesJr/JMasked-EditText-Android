A Simple masked for brazilian fields like Cep, Cnpj, Cpf and PhoneNumber (tel and cel). 
Available for Android only.


How to use:
Go to your activity.


    private EditText etSample;
    private EditText etSample2;
    private EditText etSample3;
    private EditText etSample4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_activity);

        etSample = (EditText)findViewById(R.id.etSample);
        etSample2 = (EditText)findViewById(R.id.etSample2);
        etSample3 = (EditText)findViewById(R.id.etSample3);
        etSample4 = (EditText)findViewById(R.id.etSample4);
        
        new JMaskedPhone(etSample,mHint).mask(); // phone and telephone
       
        new JMaskedCep(etSample2,mHint).mask(); // cep
        
        new JMaskedCnpj(etSample3,mHint).mask(); // cnpj
        
        new JMaskedCpf(etSample4,mHint).mask(); // cpf
        
    }
