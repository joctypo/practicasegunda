package com.example.practicados;

public class Pregunta {

    private int A;
    private int B;
    private String operador;
    private String[] operandos={"+","-","*","/"};

    //Constructor
    public Pregunta(){
        this.A=(int)(Math.random()*11);
        this.B=(int)(Math.random()*11);
        int operadorRandom=(int)(Math.random()*4);
        this.operador= operandos[operadorRandom];
    }

    //Atributos


    //Métodos
    public String getPregunta(){
     return A+" "+operador+" "+B;

    }

    public int getRespuesta(){
        int respuestica=0;
        switch (operador){
            case "+":
                respuestica= A+B;
                break;

            case "-":
                respuestica=A-B;
                break;

            case "*":
                respuestica=A*B;
                break;

            case "/":
                respuestica=A/B;
                break;
        }

        return respuestica;

    }



}
