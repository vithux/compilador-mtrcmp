package utils;

public abstract class Report {

    public abstract String getReportName();

    public abstract String getReportContent();

    public String getReport() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("###");
        stringBuilder.append(" ");
        stringBuilder.append(getReportName());
        stringBuilder.append(" ");
        stringBuilder.append("###");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append(getReportContent());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
