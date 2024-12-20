package gitlet;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Abdelrahman Ezz
 */


/** TODO
 * A commit, therefore, will consist of
 * a log message, timestamp, a mapping of file names to blob references,
 * a parent reference, and (for merges) a second parent reference.
 */



public class Commit implements Serializable {

    /** The message of this Commit. */
    private String message;
    /** The branch of this Commit. */
    private String branch;
    /** The first parent of this Commit. */
    private String parent;
    /** The second parent of this Commit. */
    private String secParent;
    /** The timeStamp of this Commit. */
    private String timeStamp;
    /** The tree structure of this Commit. */
    private String tree;
    /** The SHA of this Commit. */
    private String SHA;

    private String setDefaultTimeStamp()
    {
//        // Create a ZonedDateTime for the epoch time (1970-01-01T00:00:00Z)
//        ZonedDateTime epochTime = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
//
//        // Define the desired format
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
//                "HH:mm:ss 'UTC', EEEE, d MMMM yyyy", Locale.ENGLISH
//        );
//
//        // Format the epoch time
//        return epochTime.format(formatter);

        // Define the UTC timestamp: 00:00:00 UTC, Thursday, 1 January 1970
        long utcTimestamp = 0; // This is the epoch time for 00:00:00 UTC, 1 January 1970

        // Create a Date object for the given timestamp
        Date date = new Date(utcTimestamp);

        // Create a formatter for the desired format
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z");

        // Set the formatter to use your local timezone
        sdf.setTimeZone(TimeZone.getDefault()); // Or specify your timezone like TimeZone.getTimeZone("Europe/Berlin")

        // Format the date
        return sdf.format(date);
    }

    public Commit(String message)
    {
        this.message = message;
        this.branch = "master";
        this.parent = null;
        this.secParent = null;
        this.tree = null;
        this.timeStamp = setDefaultTimeStamp();
    }

    public Commit(String message, String branch)
    {
        this.message = message;
        this.branch = branch;
        this.parent = null;
        this.secParent = null;
        this.tree = null;
        this.timeStamp = setDefaultTimeStamp();
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    public long getMessageLength()
    {
        return this.message.length();
    }

    public void setBranch(String branch)
    {
        this.branch = branch;
    }

    public String getBranch()
    {
        return this.branch;
    }

    public void setParent(String parent)
    {
        this.parent = parent;
    }

    public String getParent()
    {
        return this.parent;
    }

    public void setSecParent(String secParent)
    {
        this.secParent = secParent;
    }

    public String getSecParent()
    {
        return this.secParent;
    }


    public void setTimeStamp(String timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp()
    {
        return this.timeStamp;
    }

    public void setTree(String tree)
    {
        this.tree = tree;
    }

    public String getTree()
    {
        return this.tree;
    }

    // Method to compute the SHA-1 hash of a tree object
    public String hashCommitObject() throws IllegalArgumentException
    {
        // Serialize the commit content
        StringBuilder commitContent = new StringBuilder();
        commitContent.append("Commit\0 ");
        if (this.tree != null) {
            commitContent.append("Tree ").append(this.tree).append("\n");
        }
        if (this.parent != null) {
            commitContent.append("Parent ").append(this.parent).append("\n");
        }
        if (this.secParent != null) {
            commitContent.append("Second parent ").append(this.secParent).append("\n");
        }
        commitContent.append("TimeStamp ").append(this.timeStamp).append("\n");
        commitContent.append(this.message).append("\n");
        this.SHA = Utils.sha1(commitContent.toString());
        return this.SHA;
    }

    public String getSHA() {
        return this.SHA;
    }

}
