package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Heath on 2/11/14.
 */
public class Job implements Parcelable{

    // ALL FIELDS IN JOB TABLE
    private int job_id;
    private String project_name;
    private int job_num;
    private String logged_by;
    private Date excavdatum;
    private String rl;
    private String equipment;
    private String operator;
    private String company;
    private String location;
    private Date excav_start;
    private Date excav_finished;

    public Job() {
        super();
    }

    // CONSTRUCTER FOR JOB
    public Job(int job_id, String project_name, int job_num, String logged_by,
               Date excavdatum, String rl, String equipment, String operator,
               String company, String location, Date excav_start,
               Date excav_finished) {
        super();
        this.job_id = job_id;
        this.project_name = project_name;
        this.job_num = job_num;
        this.logged_by = logged_by;
        this.excavdatum = excavdatum;
        this.rl = rl;
        this.equipment = equipment;
        this.operator = operator;
        this.company = company;
        this.location = location;
        this.excav_start = excav_start;
        this.excav_finished = excav_finished;
    }

    // PARCE CONSTRUCTER THINGY
    private Job(Parcel in) {
        super();
        this.job_id = in.readInt();
        this.project_name = in.readString();
        this.job_num = in.readInt();
        this.logged_by = in.readString();
        this.excavdatum = new Date(in.readLong());
        this.rl = in.readString();
        this.equipment = in.readString();
        this.operator = in.readString();
        this.company = in.readString();
        this.location = in.readString();
        this.excav_start = new Date(in.readLong());
        this.excav_finished = new Date(in.readLong());
    }




    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getJobId());
        parcel.writeString(getProjectName());
        parcel.writeInt(getJobNum());
        parcel.writeString(getLoggedBy());
        parcel.writeLong(getExcavdatum().getTime());
        parcel.writeString(getRL());
        parcel.writeString(getEquipment());
        parcel.writeString(getCompany());
        parcel.writeString(getLoaction());
        parcel.writeLong(getExcavStart().getTime());
        parcel.writeLong(getExcavFinished().getTime());

    }

    public static final Parcelable.Creator<Job> CREATOR = new Parcelable.Creator<Job>() {

        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Job[size];
        }

    };

   // @Override
    //public boolean equals(Object obj)
    //{
     //   if(this == obj)
     //       return true;
    //    if (obj == null)
    //        return false;
    //    if (getClass() != obj.getClass())
    //        return false;
     //   Job other = (Job) obj;
    //    if (job_id != other.job_id)
    //        return false;
   //     return true;
    //}


    // MAKE GET & SET METHODS FOR ALL FIELDS
    public int getJobId() {
        return job_id;
    }

    public void setJobId(int job_id) {
        this.job_id = job_id;
    }

    public String getProjectName() {
        return project_name;
    }

    public void setProjectName(String project_name) {
        this.project_name = project_name;
    }

    public int getJobNum() {
        return job_num;
    }

    public void setJobNum(int job_num) {
        this.job_num = job_num;
    }

    public String getLoggedBy() {
        return logged_by;
    }

    public void setLoggedBy(String logged_by) {
        this.logged_by = logged_by;
    }

    public Date getExcavdatum(){
        return excavdatum;
    }

    public void setExcavdatum(Date excavdatum){
        this.excavdatum = excavdatum;
    }

    public String getRL(){
        return rl;
    }

    public void setRL(String rl){
        this.rl = rl;
    }

    public String getEquipment(){
        return equipment;
    }

    public void setEquipment(String equipment){
        this.equipment = equipment;
    }

    public String getOperator(){
        return operator;
    }

    public void setOperator(String operator){
        this.operator = operator;
    }

    public String getCompany(){
        return company;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getLoaction(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public Date getExcavStart(){
        return excav_start;
    }

    public void setExcavStart(Date excav_start){
        this.excav_start = excav_start;
    }

    public Date getExcavFinished(){
        return excav_finished;
    }

    public void setExcavFinished(Date excav_finished){
        this.excav_finished = excav_finished;
    }

    //END OF GET & SET FOR FIELDS



}
