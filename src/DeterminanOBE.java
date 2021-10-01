public class DeterminanOBE{
  double calcDeterminanOBE(double[][] inputMat, int nrow, int ncol){
    GaussMatrix Gauss = new GaussMatrix(inputMat,nrow,ncol);
    return Gauss.getDeterminan();
  }
}
