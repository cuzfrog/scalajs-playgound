/*
 * This class is copied from java library.
 */
package java.time.format

import java.time.DateTimeException


/**
  * An exception thrown when an error occurs during parsing.
  * <p>
  * This exception includes the text being parsed and the error index.
  *
  * This class is intended for use in a single thread.
  *
  * @since 1.8
  */
class DateTimeParseException(message: String, cause: Throwable)
  extends DateTimeException(message, cause) {
  /**
    * The text that was being parsed.
    */
  final private var parsedString: String = _
  /**
    * The error index in the text.
    */
  final private var errorIndex = 0
  /**
    * Constructs a new exception with the specified message.
    *
    * @param message    the message to use for this exception, may be null
    * @param parsedData the parsed text, should not be null
    * @param errorIndex the index in the parsed string that was invalid, should be a valid index
    */
  def this(message: String, parsedData: CharSequence, errorIndex: Int) = {
    this(message, null)
    this.parsedString = parsedData.toString
    this.errorIndex = errorIndex
  }
  /**
    * Constructs a new exception with the specified message and cause.
    *
    * @param message    the message to use for this exception, may be null
    * @param parsedData the parsed text, should not be null
    * @param errorIndex the index in the parsed string that was invalid, should be a valid index
    * @param cause      the cause exception, may be null
    */
  def this(message: String, parsedData: CharSequence, errorIndex: Int, cause: Throwable) = {
    this(message, cause)
    this.parsedString = parsedData.toString
    this.errorIndex = errorIndex
  }
  /**
    * Constructs a new exception with the specified message.
    *
    * @param message the message to use for this exception, may be null
    */
  def this(message: String) = this(message, null)
  /**
    * Returns the string that was being parsed.
    *
    * @return the string that was being parsed, should not be null.
    */
  def getParsedString: String = parsedString
  /**
    * Returns the index where the error was found.
    *
    * @return the index in the parsed string that was invalid, should be a valid index
    */
  def getErrorIndex: Int = errorIndex
}
