public class FileInput {
    private final String Name;
    private final String Setting;
    private final String Date;
    private final String UsedFor;



    public FileInput(String fullLine){
        String[] lineSplit = fullLine.split(",");
        this.Name = lineSplit[0];
        this.Setting = lineSplit[1];
        this.Date = lineSplit[2];
        this.UsedFor = lineSplit[3];

    }

    public String getDate() {
        return Date;
    }

    public String getName() {
        return Name;
    }

    public String getSetting() {
        return Setting;
    }

    public String getUsedFor() {
        return UsedFor;
    }

}
