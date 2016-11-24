module org.rapidpm.workshop.java09.jep264.v001.logger {
  provides java.lang.System.LoggerFinder
      with org.rapidpm.workshop.java09.jep264.v001.logger.SysOutLoggerFinder;
}
