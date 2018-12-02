package tools;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;


public class ToLoggerPrintStream {
    /**
     * Logger for this class
     */
    private Logger myLog;
    private PrintStream myPrintStream;

    /**
     * @return printStream
     * @throws UnsupportedEncodingException
     */
    public PrintStream getPrintStream() {
        if (myPrintStream == null) {
            OutputStream output = new OutputStream() {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                @Override
                public void write(int b) throws IOException {

                    baos.write(b);

                }

                /**
                 * @see java.io.OutputStream#flush()
                 */
                @Override
                public void flush() {

                    String log = this.baos.toString().trim();

                    if (!StringUtils.isBlank(log)) {
                        myLog.info(log);
                        baos = new ByteArrayOutputStream();
                    }
                }
            };
            // true: autoflush
            // must be set!
            myPrintStream = new PrintStream(output, true);

        }
        return myPrintStream;
    }

    /**
     * Constructor
     * @param logger
     */
    public ToLoggerPrintStream(Logger logger) {
        super();
        myLog = logger;
    }
}
