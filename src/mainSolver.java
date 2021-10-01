import java.lang.Math;

public class mainSolver {
    public static String DeterminanMat(int getmethod){
        String hasil="Hasil Determinan = ";
        //Gery
        switch(getmethod){
            case 1:
                DeterminanOBE detByOBE=new DeterminanOBE();
                double solDetOBE=detByOBE.calcDeterminanOBE(mainFile.problem, mainFile.nrow, mainFile.nrow);
                hasil+=solDetOBE;
                break;
            case 2:
                double solutionDet = inversMatriks.DetbyKofaktor(mainFile.problem);
                hasil+=solutionDet;
                break;
        }
        return hasil;
    }

    public static String Interpolasi(){
        String hasil="Hasil Interpolasi =\ny = ";
        GaussJordanMatrix gaussjordan = new GaussJordanMatrix(mainFile.problem, mainFile.nrow, mainFile.nrow+1);
        mainFile.solution=gaussjordan.getResult(gaussjordan.getJordanMatrix(), mainFile.nrow, mainFile.nrow+1);
        int k;
        if(mainFile.solution[mainFile.nrow-1][0]==1){
            k=mainFile.nrow-1;
            hasil+="x^"+k;
        }else if(mainFile.solution[mainFile.nrow-1][0]==-1){
            k=mainFile.nrow-1;
            hasil+="-x^"+k;
        }else{
            k=mainFile.nrow-1;
            hasil+=mainFile.solution[mainFile.ncol-1][0]+"x^"+k;
        }
        for(int i=mainFile.nrow-2;i>0;i--){
            if(mainFile.solution[i][0]==0)continue;
            if(mainFile.solution[i][0]>0){
                if(mainFile.solution[i][0]==1){
                    hasil+=" + x^"+i;
                }else{
                    hasil+=" + "+mainFile.solution[i][0]+"x^"+k;
                }
            }else{
                if(mainFile.solution[i][0]==-1){
                    hasil+=" - x^"+i;
                }else{
                    double koef=mainFile.solution[i][0]*(-1d);
                    hasil+=" - "+koef+"x^"+k;
                }
            }
        }
        if(mainFile.solution[0][0]>0){
            hasil+=" + "+mainFile.solution[0][0];
        }else if(mainFile.solution[0][0]!=0){
            double koef=mainFile.solution[0][0]*(-1d);
            hasil+=" - "+koef;
        }
        hasil+="\n";

        double yres=0;

        for(int i=mainFile.nrow-1;i>=0;i--){
            yres+=mainFile.solution[i][0]*Math.pow(mainFile.xpred,i);
        }
        
        hasil+="Hasil prediksi dari x = "+yres+"\n";


        return hasil;
    }

    public static String RegresiSolver(double xpred){
        String hasil = "Hasil regresi = \ny = ";
        double[] hasilReg;
        double resultRRR = 0;
        hasilReg = regresiLinear.multiReg(mainFile.problem, mainFile.problem2);
        int i;
        int k=1;
        if(hasilReg[0]>0){
            if(hasilReg[0]==1){
                hasil+="x"+k; 
            }else{
                hasil+=hasilReg[0]+"x"+k;
            }
        }else{
            if(hasilReg[0]<0){
                if(hasilReg[0]==-1){
                    hasil+="-x"+k;
                }else{
                    double koef=hasilReg[0]*(-1d);
                    hasil+=koef+"x"+k;
                }
            }
        }
        for(i = 1; i < hasilReg.length; i++) {
            if(hasilReg[i]==0)continue;
            k=i+1;
            if(hasilReg[i]>0){
                if(hasilReg[i]==1){
                    hasil+=" + x"+k; 
                }else{
                    hasil+=" + "+hasilReg[i]+"x"+k;
                }
            }else{
                if(hasilReg[i]<0){
                    if(hasilReg[i]==-1){
                        hasil+=" - x"+k;
                    }else{
                        double koef=hasilReg[i]*(-1d);
                        hasil+=" - "+koef+"x"+k;
                    }
                }
            }
        }
        for(i = 0; i < hasilReg.length; i++) {
            if(i == 0) {
                resultRRR += hasilReg[0];
            }
            else {
                resultRRR += hasilReg[i] * xpred;
            }
        }
        hasil+="\n";
        hasil+="Hasil prediksi = "+resultRRR;
        return hasil;
    }

    public static void InverseMat(int getmethod){
        //Gery
        switch(getmethod){
            case 1:
                mainFile.solution = inversMatriks.inversebyGaussJordan(mainFile.problem);
                break;
            case 2:
                mainFile.solution = inversMatriks.inversebyKofaktor(mainFile.problem);
                break;
        }
    }

    public static String LinearEq(int getmethod){
        String hasil="";
        switch(getmethod){
            case 1:
                GaussMatrix gauss = new GaussMatrix(mainFile.problem, mainFile.nrow, mainFile.ncol);
                mainFile.solution = gauss.getResult(gauss.getGaussMatrix(), mainFile.nrow, mainFile.ncol);
                hasil="";
                if(gauss.numParams()<0){
                    hasil=hasil+"Persamaan tidak memiliki solusi\n";
                }
                else{
                    hasil="Hasil =\n";
                    int numparam=gauss.numParams();
                    for(int i=0;i<mainFile.ncol-1;i++){
                        boolean isAllZero=true;
                        int nums=i+1;
                        hasil+="x"+nums+" = ";
                        for(int j=0;j<numparam;j++){
                            if(mainFile.solution[i][j]==0)continue;
                            int k=j+1;
                            if(isAllZero && mainFile.solution[i][j]!=0){
                                if(mainFile.solution[i][j]==1){
                                    hasil+="t"+k;
                                }else if(mainFile.solution[i][j]==-1){
                                    hasil+="-t"+k;
                                }else{
                                    hasil+=mainFile.solution[i][j]+"t"+k;
                                }
                                isAllZero=false;
                            }else{
                                if(mainFile.solution[i][j]>0){
                                    if(mainFile.solution[i][j]==1){
                                        hasil+=" + "+"t"+k;
                                    }else{
                                        hasil+=" + "+mainFile.solution[i][j]+"t"+k;
                                    }
                                }else{
                                    double kof=mainFile.solution[i][j];
                                    kof*=(-1d);
                                    if(mainFile.solution[i][j]==-1){
                                        hasil+=" - "+"t"+k;
                                    }else{
                                        hasil+=" - "+kof+"t"+k;
                                    }
                                }
                            }
                        }
                        if(isAllZero){
                            hasil+=mainFile.solution[i][numparam];
                        }else{
                            if(mainFile.solution[i][numparam]!=0){
                                if(mainFile.solution[i][numparam]>0){
                                    hasil+=" + "+mainFile.solution[i][numparam];
                                }else{
                                    double kof=mainFile.solution[i][numparam];
                                    kof*=(-1d);
                                    hasil+=" - "+kof;
                                }
                            }
                        }
                        hasil+="\n";
                    }
                }
                break;
            case 2:
                GaussJordanMatrix gaussjordan = new GaussJordanMatrix(mainFile.problem, mainFile.nrow, mainFile.ncol);
                mainFile.solution = gaussjordan.getResult(gaussjordan.getJordanMatrix(), mainFile.nrow, mainFile.ncol);
                hasil="";
                if(gaussjordan.numParams()<0){
                    hasil=hasil+"Persamaan tidak memiliki solusi\n";
                }
                else{
                    hasil="Hasil =\n";
                    int numparam=gaussjordan.numParams();
                    for(int i=0;i<mainFile.ncol-1;i++){
                        boolean isAllZero=true;
                        int nums=i+1;
                        hasil+="x"+nums+" = ";
                        for(int j=0;j<numparam;j++){
                            if(mainFile.solution[i][j]==0)continue;
                            int k=j+1;
                            if(isAllZero && mainFile.solution[i][j]!=0){
                                if(mainFile.solution[i][j]==1){
                                    hasil+="t"+k;
                                }else if(mainFile.solution[i][j]==-1){
                                    hasil+="-t"+k;
                                }else{
                                    hasil+=mainFile.solution[i][j]+"t"+k;
                                }
                                isAllZero=false;
                            }else{
                                if(mainFile.solution[i][j]>0){
                                    if(mainFile.solution[i][j]==1){
                                        hasil+=" + "+"t"+k;
                                    }else{
                                        hasil+=" + "+mainFile.solution[i][j]+"t"+k;
                                    }
                                }else{
                                    double kof=mainFile.solution[i][j];
                                    kof*=(-1d);
                                    if(mainFile.solution[i][j]==-1){
                                        hasil+=" - "+"t"+k;
                                    }else{
                                        hasil+=" - "+kof+"t"+k;
                                    }
                                }
                            }
                        }
                        if(isAllZero){
                            hasil+=mainFile.solution[i][numparam];
                        }else{
                            if(mainFile.solution[i][numparam]!=0){
                                if(mainFile.solution[i][numparam]>0){
                                    hasil+=" + "+mainFile.solution[i][numparam];
                                }else{
                                    double kof=mainFile.solution[i][numparam];
                                    kof*=(-1d);
                                    hasil+=" - "+kof;
                                }
                            }
                        }
                        hasil+="\n";
                    }
                }
                break;
            case 4:
                //Gery
                double[] resultt;
                hasil = "";
                int m = 1;
                double[][] matriksNew = new double[mainFile.nrow][mainFile.nrow];
                if(mainFile.nrow == (mainFile.ncol - 1)) {
                    for(int i = 0; i < mainFile.nrow; i++) {
                        for(int j = 0; j < mainFile.nrow; j++) {
                            matriksNew[i][j] = mainFile.problem[i][j];
                        }
                    }
                    if(inversMatriks.DetbyKofaktor(matriksNew) != 0) {
                        resultt = SPLCrammer.Crammer(matriksNew, mainFile.b_spl);
                        for(int i = 0; i < mainFile.nrow; i++) {
                            m = i + 1;
                            hasil += "x" + m + " = " + resultt[i] + "\n";
                        }
                    }
                    else{
                        hasil += "SPL tidak memiliki solusi unik";
                    }
                }
                break;
            case 3:
                double[] resulttt;
                hasil = "";
                int mm = 1;
                double[][] matriksNeww = new double[mainFile.nrow][mainFile.nrow];
                if(mainFile.nrow == (mainFile.ncol - 1)) {
                    for(int i = 0; i < mainFile.nrow; i++) {
                        for(int j = 0; j < mainFile.nrow; j++) {
                            matriksNeww[i][j] = mainFile.problem[i][j];
                        }
                    }
                    if(inversMatriks.DetbyKofaktor(matriksNeww) != 0) {
                        double[][] tempo = inversMatriks.inversebyKofaktor(matriksNeww);
                        resulttt = regresiLinear.multiplication(tempo, mainFile.b_spl);
                        for(int i = 0; i < mainFile.nrow; i++) {
                            mm = i + 1;
                            hasil += "x" + mm + " = " + resulttt[i] + "\n";
                        }
                    }
                    else{
                        hasil += "SPL tidak memiliki solusi unik";
                    }
                }
                break;
        }
        return hasil;
    }
}
