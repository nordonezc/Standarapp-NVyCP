/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standarapp.algorithm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Niki
 */
public class CodeAssign {

    private static Hashtable<String, Hashtable<String, Hashtable<String, Double>>> diccionario_UbicacionLocalidad;
    private static Hashtable<Integer, String> codigo_Dpto;
    private static Hashtable<Integer, String> codigo_Municipio;
    private static Hashtable<Integer, Hashtable<Integer, String>> dpto_Municipio;
    private static Hashtable<Double, String> codigo_localidad;
    private static Hashtable<Double, Double> localidad_X;
    private static Hashtable<Double, Double> localidad_Y;
    private static Hashtable<Integer, Hashtable<Double, String>> codigo_municipioLocalidad;

    public static Hashtable<Integer, String> getCodigo_Dpto() {
        return codigo_Dpto;
    }

    public static Hashtable<Integer, String> getCodigo_Municipio() {
        return codigo_Municipio;
    }

    public static Hashtable<Integer, Hashtable<Integer, String>> getDpto_Municipio() {
        return dpto_Municipio;
    }

    public static Hashtable<Double, String> getCodigo_localidad() {
        return codigo_localidad;
    }

    public static Hashtable<String, Hashtable<String, Hashtable<String, Double>>> getDiccionario_UbicacionLocalidad() {
        return diccionario_UbicacionLocalidad;
    }

    public static Hashtable<Double, Double> getLocalidad_X() {
        return localidad_X;
    }

    public static Hashtable<Double, Double> getLocalidad_Y() {
        return localidad_Y;
    }

    public static Hashtable<Integer, Hashtable<Double, String>> getCodigo_municipioLocalidad() {
        return codigo_municipioLocalidad;
    }

    public CodeAssign(String nameExcel) throws IOException {
        //Logica de la aplicacion
        diccionario_UbicacionLocalidad = new Hashtable<>();
        codigo_Dpto = new Hashtable<>();
        codigo_Municipio = new Hashtable<>();
        dpto_Municipio = new Hashtable<>();
        codigo_localidad = new Hashtable<>();
        localidad_X = new Hashtable<>();
        localidad_Y = new Hashtable<>();
        codigo_municipioLocalidad = new Hashtable<>();

        XSSFWorkbook xwb = Lecture.lectureXLSX(nameExcel);
        XSSFSheet xsheet = xwb.getSheetAt(0);
        double codigoTemporal = 0;

        for (Row row : xsheet) {
            if (row.getRowNum() > 0) {
                String departamento = "", municipio = "", localidad = "";
                int cod_departamento = 0, cod_municipio = 0;
                double cod_localidad = 0, x = 0, y = 0;
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                codigoTemporal = Double.valueOf(cell.getStringCellValue());
                                cod_departamento = (int) codigoTemporal;
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                cod_departamento = (int) cell.getNumericCellValue();
                                continue;
                        }
                    }

                    if (cell.getColumnIndex() == 1) {
                        departamento = cell.getStringCellValue();
                    }

                    if (cell.getColumnIndex() == 2) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                codigoTemporal = Double.valueOf(cell.getStringCellValue());
                                cod_municipio = (int) codigoTemporal;
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                cod_municipio = (int) cell.getNumericCellValue();
                                continue;
                        }
                    }

                    if (cell.getColumnIndex() == 3) {
                        municipio = cell.getStringCellValue();
                    }

                    if (cell.getColumnIndex() == 4) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                cod_localidad = Double.valueOf(cell.getStringCellValue());
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                cod_localidad = (double) cell.getNumericCellValue();
                                continue;
                        }
                    }

                    if (cell.getColumnIndex() == 5) {
                        localidad = cell.getStringCellValue();
                    }

                    if (cell.getColumnIndex() == 6) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                x = Double.valueOf(cell.getStringCellValue());
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                x = (double) cell.getNumericCellValue();
                                continue;
                        }
                    }

                    if (cell.getColumnIndex() == 7) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                y = Double.valueOf(cell.getStringCellValue());
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                y = (double) cell.getNumericCellValue();
                                continue;
                        }
                    }
                }

                if (!diccionario_UbicacionLocalidad.containsKey(departamento)) {
                    Hashtable<String, Hashtable<String, Double>> primerMunicipio = new Hashtable<>();
                    Hashtable<String, Double> primerLocalidad = new Hashtable<>();
                    Hashtable<Double, String> primerLocalidadInv = new Hashtable<>();
                    codigo_Dpto.put(cod_departamento, departamento);
                    codigo_Municipio.put(cod_municipio, municipio);
                    codigo_localidad.put(cod_localidad, localidad);
                    localidad_X.put(cod_localidad, x);
                    localidad_Y.put(cod_localidad, y);

                    primerLocalidadInv.put(cod_localidad, localidad);
                    codigo_municipioLocalidad.put(cod_municipio, primerLocalidadInv);
                    primerLocalidad.put(localidad, cod_localidad);
                    primerMunicipio.put(municipio, primerLocalidad);
                    diccionario_UbicacionLocalidad.put(departamento, primerMunicipio);

                } else if (!diccionario_UbicacionLocalidad.get(departamento).containsKey(municipio)) {
                    Hashtable<String, Double> primerLocalidad = new Hashtable<>();
                    codigo_Municipio.put(cod_municipio, municipio);
                    codigo_localidad.put(cod_localidad, localidad);
                    localidad_X.put(cod_localidad, x);
                    localidad_Y.put(cod_localidad, y);
                    primerLocalidad.put(localidad, cod_localidad);

                    Hashtable<Double, String> primerLocalidadInv = new Hashtable<>();

                    primerLocalidadInv.put(cod_localidad, localidad);
                    codigo_municipioLocalidad.put(cod_municipio, primerLocalidadInv);

                    diccionario_UbicacionLocalidad.get(departamento).put(municipio, primerLocalidad);

                } else if (!diccionario_UbicacionLocalidad.get(departamento).get(municipio).containsKey(localidad)) {
                    codigo_localidad.put(cod_localidad, localidad);
                    localidad_X.put(cod_localidad, x);
                    localidad_Y.put(cod_localidad, y);
                    codigo_municipioLocalidad.get(cod_municipio).put(cod_localidad, localidad);
                    diccionario_UbicacionLocalidad.get(departamento).get(municipio).put(localidad, cod_localidad);
                }
            }
        }

    }

    public void showDictionary() {
        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                for (String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()) {
                    System.out.println("Dpto: " + dpto + " | Mncp: " + mncp + " | Local: " + local + " | Cod: " + diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(local));
                }
            }
        }
    }

    public void showMncpLocalidad() {
        int cont = 0;
        for (Integer mncp : codigo_municipioLocalidad.keySet()) {
            for (Double local : codigo_municipioLocalidad.get(mncp).keySet()) {
                System.out.println(cont++ + "Mncp: " + mncp + " | Local: " + local + " | " + codigo_municipioLocalidad.get(mncp).get(local));
            }
        }
    }

    public String findByDepartamentoCode(Integer dpto) {
        String answer = "El codigo de Departamento corresponde a: " + codigo_Dpto.get(dpto);

        try {
            for (Integer mncp : codigo_Municipio.keySet()) {
                if (String.valueOf(mncp).startsWith(String.valueOf(dpto))) {
                    answer += ". Municipio: " + codigo_Municipio.get(mncp);
                    answer += ". Codigo: " + mncp + "\n";
                }
            }
        } catch (Exception e) {
            answer = "No existe departamento correspondiente al codigo ingresado.";
        }
        return answer;
    }

    public String findByDepartamentoString(String departamento) {
        String answer = "";
        String departamentoCorrecto = departamento;
        if (!diccionario_UbicacionLocalidad.containsKey(departamentoCorrecto)) {
            int majorLev = 50;
            for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
                int partialLev = FuzzySearch.ratio(dpto, departamento);
                if (partialLev > majorLev) {
                    departamentoCorrecto = dpto;
                    majorLev = partialLev;
                }
                if (majorLev >= 80) {
                    break;
                }
            }
        }

        answer += "El departamento " + departamentoCorrecto + " tiene: \n";
        for (String mncp : diccionario_UbicacionLocalidad.get(departamentoCorrecto).keySet()) {
            answer += mncp + "\n";
        }
        return answer;
    }

    public String finbByDepartamentoCodeAndMunicipioString(Integer dptoC, String municipioN) {
        String answer = "";

        if(!codigo_Dpto.contains(dptoC)){
            return "No se encuentra ningun departamento asociado a dicho codigo";
        }
        
        String nombreDpto = codigo_Dpto.get(dptoC);
        String mncpCorrect = municipioN;

        if (!diccionario_UbicacionLocalidad.get(nombreDpto).containsKey(municipioN)) {
            int majorLev = 50;
            for (String mncp : diccionario_UbicacionLocalidad.get(nombreDpto).keySet()) {
                int partialLev = FuzzySearch.ratio(mncp, municipioN);
                if (partialLev > majorLev) {
                    mncpCorrect = mncp;
                    majorLev = partialLev;
                }
                if (majorLev >= 80) {
                    break;
                }
            }
        }

        answer += "Departamento: " + codigo_Dpto.get(dptoC)
                + ". Municipio: " + mncpCorrect;

        return answer;
    }

    public String findByMunicipioCode(Integer mncp) {
        String answer = "El codigo corresponde a: \n";
        
        if(!codigo_Municipio.contains(mncp))
            return "No se encuentra un municipio asociado a dicho codigo";
        
        int cod_dpto = 0;
        String municipio = String.valueOf(mncp);
        if (municipio.length() == 4) {
            cod_dpto = Integer.parseInt(municipio.substring(0, 1));
        } else {
            cod_dpto = Integer.parseInt(municipio.substring(0, 2));
        }

        answer += "Departamento: " + codigo_Dpto.get(cod_dpto)
                + ". Municipio: " + codigo_Municipio.get(mncp)
                + ". Codigo: " + mncp;

        return answer;
    }

    public String findByMunicipioString(String municipio) {
        String answer = "";
        String dptoCorrecto = "";
        answer += "El municicpio " + dptoCorrecto + " esta en: \n";
        int majorLev = 50;

        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                int partialLev = FuzzySearch.ratio(mncp, municipio);
                if (partialLev > 50) {
                    if (partialLev > majorLev) {
                        dptoCorrecto = dpto;
                        majorLev = partialLev;
                    }
                    answer += dpto;
                    if (partialLev >= 100) {
                        answer += " *MUY probable* " + partialLev;
                    } else if (partialLev >= 80) {
                        answer += " *probable* " + partialLev;
                    }
                    answer += "\n";
                    break;
                }
            }
        }
        answer += dptoCorrecto + " *MUY probable* " + majorLev;
        return answer;
    }

    public String findByLocalidadesCode(Double localidad) {
        String answer = "";
        int cod_dpto = 0;
        int cod_mncp = 0;
        String local = String.valueOf(localidad);
        try {
            /*if (local.length() == 6 || local.length() == 13) {
            cod_dpto = Integer.parseInt(local.substring(0, 1));
            cod_mncp = Integer.parseInt(local.substring(0, 4));
        } else {
            cod_dpto = Integer.parseInt(local.substring(0, 2));
            cod_mncp = Integer.parseInt(local.substring(0, 5));
        }*/

            answer
                    += //"Departamento: " + codigo_Dpto.get(cod_dpto)
                    //+ ". Municipio: " + codigo_Municipio.get(cod_mncp)
                    ". Localidad: " + codigo_localidad.get(localidad)
                    + ". Codigo: " + localidad
                    + ". X: " + localidad_X.get(localidad)
                    + ". Y: " + localidad_Y.get(localidad) + "\n";
        } catch (Exception e) {
            answer = "No se pudo encontrar la localidad con dicho codigo";
        }
        return answer;
    }

    public String findByLocalidadString(String localidad) {
        String answer = "La localidad " + localidad + " esta en: \n";
        String localidadCorrecta = localidad;
        int majorLev = 50;
        int percent = 80;

        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                for (String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()) {
                    int partialLev = 0;
                    partialLev = FuzzySearch.tokenSetRatio(local, localidad);

                    if (partialLev >= majorLev && partialLev >= percent) {
                        localidadCorrecta = local;
                        majorLev = partialLev;
                    }
                    if (majorLev >= percent) {
                        majorLev = 0;
                        answer += "Dpto: " + dpto + ". Municipio: " + mncp + ". Localidad: "
                                + local + ". Codigo: " + diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta)
                                + ". X: " + localidad_X.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta))
                                + ". Y:" + localidad_Y.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta));
                        //break;

                        if (partialLev == 100) {
                            answer += ". Muy probable. ";
                        }
                        answer += "\n";
                    }
                }
            }
        }

        return answer;
    }

    public String findByDptoMncpCodeAndLocalidadString(Integer dptoCode, Integer mncpCode, String localidad) {
        String answer = "La localidad " + localidad + " esta en: \n";
        String localidadCorrecta = localidad;
        int majorLev = 50;
        int percent = 80;
        String dpto = "";
        String mncp = "";

        if (!codigo_Dpto.containsKey(dptoCode)) {
            return "No existe departamento con dicho codigo";
        } else if (!codigo_Municipio.containsKey(mncpCode)) {
            return "No existe municipio con dicho codigo";
        } else {
            dpto = codigo_Dpto.get(dptoCode);
            mncp = codigo_Municipio.get(mncpCode);
        }

        for (String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()) {
            int partialLev = 0;
            partialLev = FuzzySearch.tokenSetRatio(local, localidad);

            if (partialLev >= majorLev && partialLev >= percent) {
                localidadCorrecta = local;
                majorLev = partialLev;
            }
            if (majorLev >= percent) {
                majorLev = 0;
                answer += "Dpto: " + dpto + ". Municipio: " + mncp + ". Localidad: "
                        + local + ". Codigo: " + diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta)
                        + ". X: " + localidad_X.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta))
                        + ". Y:" + localidad_Y.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta));
                //break;

                if (partialLev == 100) {
                    answer += ". Muy probable. ";
                }
                answer += "\n";

            }
        }

        return answer;
    }

    public String findByDptoCodeAndLocalidadString(Integer dptoCode, String localidad) {
        String answer = "La localidad " + localidad + " esta en: \n";
        String localidadCorrecta = localidad;
        int majorLev = 50;
        int percent = 80;
        String dpto = "";

        if (!codigo_Dpto.containsKey(dptoCode)) {
            return "No existe municipio con dicho codigo";
        } else {
            dpto = codigo_Dpto.get(dptoCode);
        }

        for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
            for (String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()) {
                int partialLev = 0;
                partialLev = FuzzySearch.tokenSetRatio(local, localidad);

                if (partialLev >= majorLev && partialLev >= percent) {
                    localidadCorrecta = local;
                    majorLev = partialLev;
                }
                if (majorLev >= percent) {
                    majorLev = 0;
                    answer += "Dpto: " + dpto + ". Municipio: " + mncp + ". Localidad: "
                            + local + ". Codigo: " + diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta)
                            + ". X: " + localidad_X.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta))
                            + ". Y:" + localidad_Y.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta));
                    //break;

                    if (partialLev == 100) {
                        answer += ". Muy probable. ";
                    }
                    answer += "\n";

                }
            }
        }

        return answer;
    }

    public String findByMncpCodeAndLocalidadString(Integer mncpCode, String localidad) {
        String answer = "La localidad " + localidad + " esta en: \n";
        String localidadCorrecta = localidad;
        int majorLev = 50;
        int percent = 80;

        if (!codigo_Municipio.containsKey(mncpCode)) {
            return "No existe municipio con dicho codigo";
        }

        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                if (mncp.equals(codigo_Municipio.get(mncpCode))) {
                    for (String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()) {
                        int partialLev = 0;
                        partialLev = FuzzySearch.tokenSetRatio(local, localidad);

                        if (partialLev >= majorLev && partialLev >= percent) {
                            localidadCorrecta = local;
                            majorLev = partialLev;
                        }
                        if (majorLev >= percent) {
                            majorLev = 0;
                            answer += "Dpto: " + dpto + ". Municipio: " + mncp + ". Localidad: "
                                    + local + ". Codigo: " + diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta)
                                    + ". X: " + localidad_X.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta))
                                    + ". Y:" + localidad_Y.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta));
                            //break;

                            if (partialLev == 100) {
                                answer += ". Muy probable. ";
                            }
                            answer += "\n";

                        }
                    }
                }
            }
        }

        return answer;
    }

    public String findByMunicipioAndDepartamentoString(String municipio, String departamento) {
        String answer = "";
        String municipioCorrecto = municipio;
        String departamentoCorrecto = departamento;
        if (!diccionario_UbicacionLocalidad.containsKey(departamentoCorrecto)) {
            int majorLev = 50;
            for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
                int partialLev = FuzzySearch.ratio(dpto, departamento);
                if (partialLev > majorLev) {
                    departamentoCorrecto = dpto;
                    majorLev = partialLev;
                }
                if (majorLev >= 50) {
                    break;
                }
            }
        }

        if (!diccionario_UbicacionLocalidad.get(departamentoCorrecto).containsKey(municipioCorrecto)) {
            int majorLev = 0;
            for (String mncp : diccionario_UbicacionLocalidad.get(departamentoCorrecto).keySet()) {
                int partialLev = FuzzySearch.ratio(mncp, municipioCorrecto);
                if (partialLev > majorLev) {
                    municipioCorrecto = mncp;
                    majorLev = partialLev;
                }
                if (majorLev >= 50) {
                    break;
                }
            }
        }

        answer += "Departamento: " + codigo_Dpto.get(departamentoCorrecto)
                + ". Municipio: " + codigo_Municipio.get(municipioCorrecto) + "\n";

        for (String localidades : diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).keySet()) {
            answer += ". Localidad: " + localidades
                    + ". Codigo: " + diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidades)
                    + ". X: " + localidad_X.get(diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidades))
                    + ". Y: " + localidad_Y.get(diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidades)) + "\n";
        }

        return answer;
    }

    public String findByLocalidadAndMunicipioCodes(Double localidad, Integer municipio) {
        String answer = "";
        int cod_dpto = 0;
        String mncp = String.valueOf(municipio);
        if (mncp.length() == 4) {
            cod_dpto = Integer.parseInt(mncp.substring(0, 1));
        } else {
            cod_dpto = Integer.parseInt(mncp.substring(0, 2));
        }

        answer += "Departamento: " + codigo_Dpto.get(cod_dpto)
                + ". Municipio: " + codigo_Municipio.get(municipio)
                + ". Localidad: " + codigo_localidad.get(localidad)
                + ". Codigo: " + localidad
                + ". X: " + localidad_X.get(localidad)
                + ". Y: " + localidad_Y.get(localidad) + "\n";

        return answer;
    }

    public String findByLocalidadAndMunicipioString(String localidad, String municipio) {
        String answer = "";
        String municipioCorrecto = municipio;
        String localidadCorrecta = localidad;
        String departamentoCorrecto = "";
        int majorLev = 0;
        int majorLev2 = 0;
        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                int partialLev = FuzzySearch.ratio(mncp, municipio);
                if (partialLev >= majorLev) {
                    municipioCorrecto = mncp;
                    majorLev = partialLev;
                    departamentoCorrecto = dpto;
                    for (String local : diccionario_UbicacionLocalidad.get(dpto).get(municipioCorrecto).keySet()) {
                        int partialLev2 = FuzzySearch.tokenSetRatio(local, localidad);
                        if (partialLev2 >= majorLev2) {
                            localidadCorrecta = local;
                            majorLev2 = partialLev2;

                            answer += "Departamento: " + departamentoCorrecto
                                    + ". Municipio: " + municipioCorrecto
                                    + ". Localidad: " + localidadCorrecta
                                    + ". Codigo: " + diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta)
                                    + ". X: " + localidad_X.get(diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta))
                                    + ". Y: " + localidad_Y.get(diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta));
                            if (majorLev2 == 100 && majorLev == 100) {
                                answer += ". Muy probable.";
                            } else {
                                answer += "\n";
                            }
                        }

                    }
                }
            }
        }

        if (answer.equals("")) {
            answer = "No se encontro ninguna localidad " + localidad + " con dicho municipio " + municipio;
        }

        return answer;
    }

    public String finbByLocalidadAndDepartamento(String localidad, String departamento) {
        String answer = "El " + departamento + " se encontro la " + localidad + " en: \n";
        String localidadCorrecta = localidad;
        String departamentoCorrecto = departamento;
        int majorLev = 50;
        if (!diccionario_UbicacionLocalidad.containsKey(departamento)) {
            for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
                if (FuzzySearch.ratio(departamentoCorrecto, dpto) < 50) {
                    continue;
                }
                for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                    for (String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()) {
                        int temporalLev = FuzzySearch.ratio(local, localidadCorrecta);
                        if (temporalLev >= majorLev) {
                            majorLev = temporalLev;
                            answer += "Departamento: " + dpto
                                    + ". Municipio: " + mncp
                                    + ". Localidad: " + local
                                    + ". Codigo: " + diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(local)
                                    + ". X: " + localidad_X.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(local))
                                    + ". Y: " + localidad_Y.get(diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(local)) + "\n";
                        }
                    }
                }
            }
        } else {
            for (String mncp : diccionario_UbicacionLocalidad.get(departamentoCorrecto).keySet()) {
                for (String local : diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(mncp).keySet()) {
                    int temporalLev = FuzzySearch.ratio(local, localidadCorrecta);
                    if (temporalLev >= majorLev) {
                        majorLev = temporalLev;
                        answer += "Departamento: " + departamentoCorrecto
                                + ". Municipio: " + mncp
                                + ". Localidad: " + local
                                + ". Codigo: " + diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(mncp).get(local)
                                + ". X: " + localidad_X.get(diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(mncp).get(local))
                                + ". Y: " + localidad_Y.get(diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(mncp).get(local)) + "\n";
                        answer += "El municipio que tiene a " + local + " y pertenece a " + departamentoCorrecto + " es: " + mncp + "\n";
                    }
                }
            }
        }
        return answer;
    }

    public String findByAllCodes(Integer departamento, Integer municipio, Double localidad) {
        String answer = "Departamento: " + codigo_Dpto.get(departamento)
                + ". Municipio: " + codigo_Municipio.get(municipio)
                + ". Localidad: " + codigo_localidad.get(localidad)
                + ". Codigo: " + localidad
                + ". X: " + localidad_X.get(localidad)
                + ". Y: " + localidad_Y.get(localidad);
        return answer;
    }

    public String findByAllString(String departamento, String municipio, String localidad) {
        String answer = "";
        String municipioCorrecto = municipio;
        String localidadCorrecta = localidad;
        String departamentoCorrecto = departamento;
        int majorLev = 0;

        if (!diccionario_UbicacionLocalidad.containsKey(departamentoCorrecto)) {
            for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
                int partialLev = FuzzySearch.ratio(dpto, departamentoCorrecto);
                if (partialLev > majorLev) {
                    majorLev = partialLev;
                    departamentoCorrecto = dpto;
                }
                if (majorLev >= 50) {
                    break;
                }
            }
            majorLev = 0;
        }

        if (!diccionario_UbicacionLocalidad.get(departamentoCorrecto).containsKey(municipioCorrecto)) {
            for (String mncp : diccionario_UbicacionLocalidad.get(departamentoCorrecto).keySet()) {
                int partialLev = FuzzySearch.ratio(mncp, municipioCorrecto);
                if (partialLev > majorLev) {
                    municipioCorrecto = mncp;
                    majorLev = partialLev;
                }
                if (majorLev >= 50) {
                    break;
                }
            }
            majorLev = 0;
        }

        if (!diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).containsKey(localidadCorrecta)) {
            for (String local : diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).keySet()) {
                int partialLev = FuzzySearch.tokenSetRatio(local, localidadCorrecta);
                if (partialLev > majorLev) {
                    localidadCorrecta = local;
                    majorLev = partialLev;
                }
                if (majorLev >= 80) {
                    break;
                }
            }
        }

        answer += "Departamento: " + departamentoCorrecto
                + ". Municipio: " + municipioCorrecto
                + ". Localidad: " + localidadCorrecta
                + ". Codigo: " + diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta)
                + ". X: " + localidad_X.get(diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta))
                + ". Y: " + localidad_Y.get(diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta));
        return answer;
    }
}
