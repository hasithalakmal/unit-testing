package com.smile.clz.api.core.config;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

/**
 * todo
 *
 * @author hasithagamage
 * @date 11/5/17
 **/

public class SmileDailyRollingFileAppender extends FileAppender {
    static final int TOP_OF_TROUBLE = -1;
    static final int TOP_OF_MINUTE = 0;
    static final int TOP_OF_HOUR = 1;
    static final int HALF_DAY = 2;
    static final int TOP_OF_DAY = 3;
    static final int TOP_OF_WEEK = 4;
    static final int TOP_OF_MONTH = 5;
    private String datePattern = "'.'yyyy-MM-dd";
    private String scheduledFilename;
    private long nextCheck = System.currentTimeMillis() - 1L;
    Date now = new Date();
    SimpleDateFormat sdf;
    SmileDailyRollingFileAppender.RollingCalendar rc = new SmileDailyRollingFileAppender.RollingCalendar();
    int checkPeriod = -1;
    static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
    private String maxNumberOfDays = "30";

    public SmileDailyRollingFileAppender() {
    }

    public SmileDailyRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
        super(layout, filename, true);
        this.datePattern = datePattern;
        this.activateOptions();
    }

    public void setDatePattern(String pattern) {
        this.datePattern = pattern;
    }

    public String getDatePattern() {
        return this.datePattern;
    }

    public String getMaxNumberOfDays() {
        return this.maxNumberOfDays;
    }

    public void setMaxNumberOfDays(String maxNumberOfDays) {
        this.maxNumberOfDays = maxNumberOfDays;
    }

    public void activateOptions() {
        super.activateOptions();
        if (this.datePattern != null && this.fileName != null) {
            this.now.setTime(System.currentTimeMillis());
            this.sdf = new SimpleDateFormat(this.datePattern);
            int type = this.computeCheckPeriod();
            this.printPeriodicity(type);
            this.rc.setType(type);
            File file = new File(this.fileName);
            this.scheduledFilename = this.fileName + this.sdf.format(new Date(file.lastModified()));
        } else {
            LogLog.error("Either File or DatePattern options are not set for appender [" + this.name + "].");
        }

    }

    void printPeriodicity(int type) {
        switch(type) {
            case 0:
                LogLog.debug("Appender [" + this.name + "] to be rolled every minute.");
                break;
            case 1:
                LogLog.debug("Appender [" + this.name + "] to be rolled on top of every hour.");
                break;
            case 2:
                LogLog.debug("Appender [" + this.name + "] to be rolled at midday and midnight.");
                break;
            case 3:
                LogLog.debug("Appender [" + this.name + "] to be rolled at midnight.");
                break;
            case 4:
                LogLog.debug("Appender [" + this.name + "] to be rolled at start of week.");
                break;
            case 5:
                LogLog.debug("Appender [" + this.name + "] to be rolled at start of every month.");
                break;
            default:
                LogLog.warn("Unknown periodicity for appender [" + this.name + "].");
        }

    }

    int computeCheckPeriod() {
        SmileDailyRollingFileAppender.RollingCalendar rollingCalendar = new SmileDailyRollingFileAppender.RollingCalendar(gmtTimeZone, Locale.getDefault());
        Date epoch = new Date(0L);
        if (this.datePattern != null) {
            for(int i = 0; i <= 5; ++i) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
                simpleDateFormat.setTimeZone(gmtTimeZone);
                String r0 = simpleDateFormat.format(epoch);
                rollingCalendar.setType(i);
                Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
                String r1 = simpleDateFormat.format(next);
                if (r0 != null && r1 != null && !r0.equals(r1)) {
                    return i;
                }
            }
        }

        return -1;
    }

    void rollOver() throws IOException {
        if (this.datePattern == null) {
            this.errorHandler.error("Missing DatePattern option in rollOver().");
        } else {
            String datedFilename = this.fileName + this.sdf.format(this.now);
            if (!this.scheduledFilename.equals(datedFilename)) {
                this.closeFile();
                File target = new File(this.scheduledFilename);
                if (target.exists()) {
                    target.delete();
                }

                File file = new File(this.fileName);
                boolean result = file.renameTo(target);
                if (result) {
                    LogLog.debug(this.fileName + " -> " + this.scheduledFilename);
                } else {
                    LogLog.error("Failed to rename [" + this.fileName + "] to [" + this.scheduledFilename + "].");
                }

                try {
                    this.setFile(this.fileName, true, this.bufferedIO, this.bufferSize);
                } catch (IOException var6) {
                    this.errorHandler.error("setFile(" + this.fileName + ", true) call failed.");
                }

                this.scheduledFilename = datedFilename;
            }
        }
    }

    protected void subAppend(LoggingEvent event) {
        long n = System.currentTimeMillis();
        if (n >= this.nextCheck) {
            this.now.setTime(n);
            this.nextCheck = this.rc.getNextCheckMillis(this.now);

            try {
                this.cleanupAndRollOver();
            } catch (IOException var5) {
                if (var5 instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }

                LogLog.error("cleanupAndRollOver() failed.", var5);
            }
        }

        super.subAppend(event);
    }

    protected void cleanupAndRollOver() throws IOException {
        File file = new File(this.fileName);
        Calendar cal = Calendar.getInstance();
        int maxDays = 30;

        try {
            maxDays = Integer.parseInt(this.getMaxNumberOfDays());
        } catch (Exception var11) {
            ;
        }

        ++maxDays;
        if (maxDays <= 0) {
            maxDays = 1;
        }

        cal.add(5, -maxDays);
        Date cutoffDate = cal.getTime();
        File[] files = null;
        if (file.getParentFile().exists()) {
            files = file.getParentFile().listFiles(new SmileDailyRollingFileAppender.StartsWithFileFilter(file.getName(), false));
            int nameLength = file.getName().length();

            for(int i = 0; i < files.length; ++i) {
                String datePart = null;

                try {
                    datePart = files[i].getName().substring(nameLength);
                    Date date = this.sdf.parse(datePart);
                    if (date.before(cutoffDate)) {
                        files[i].delete();
                    }
                } catch (Exception var10) {
                    ;
                }
            }
        }

        this.rollOver();
    }

    class RollingCalendar extends GregorianCalendar {
        private static final long serialVersionUID = -3560331770601814177L;
        int type = -1;

        RollingCalendar() {
        }

        RollingCalendar(TimeZone tz, Locale locale) {
            super(tz, locale);
        }

        void setType(int type) {
            this.type = type;
        }

        public long getNextCheckMillis(Date now) {
            return this.getNextCheckDate(now).getTime();
        }

        public Date getNextCheckDate(Date now) {
            this.setTime(now);
            switch(this.type) {
                case 0:
                    this.set(13, 0);
                    this.set(14, 0);
                    this.add(12, 1);
                    break;
                case 1:
                    this.set(12, 0);
                    this.set(13, 0);
                    this.set(14, 0);
                    this.add(11, 1);
                    break;
                case 2:
                    this.set(12, 0);
                    this.set(13, 0);
                    this.set(14, 0);
                    int hour = this.get(11);
                    if (hour < 12) {
                        this.set(11, 12);
                    } else {
                        this.set(11, 0);
                        this.add(5, 1);
                    }
                    break;
                case 3:
                    this.set(11, 0);
                    this.set(12, 0);
                    this.set(13, 0);
                    this.set(14, 0);
                    this.add(5, 1);
                    break;
                case 4:
                    this.set(7, this.getFirstDayOfWeek());
                    this.set(11, 0);
                    this.set(12, 0);
                    this.set(13, 0);
                    this.set(14, 0);
                    this.add(3, 1);
                    break;
                case 5:
                    this.set(5, 1);
                    this.set(11, 0);
                    this.set(12, 0);
                    this.set(13, 0);
                    this.set(14, 0);
                    this.add(2, 1);
                    break;
                default:
                    throw new IllegalStateException("Unknown periodicity type.");
            }

            return this.getTime();
        }
    }

    class StartsWithFileFilter implements FileFilter {
        private String startsWith;
        private boolean inclDirs = false;

        public StartsWithFileFilter(String startsWith, boolean includeDirectories) {
            this.startsWith = startsWith.toUpperCase();
            this.inclDirs = includeDirectories;
        }

        public boolean accept(File pathname) {
            return !this.inclDirs && pathname.isDirectory() ? false : pathname.getName().toUpperCase().startsWith(this.startsWith);
        }
    }
}
