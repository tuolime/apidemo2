 package com.example.protocol.exception;
 
 public class InvalidNumericConvertException extends RuntimeException
 {
   private static final long serialVersionUID = 8816726967201329425L;
 
   public InvalidNumericConvertException()
   {
   }
 
   public InvalidNumericConvertException(String message)
   {
     super(message);
   }
 
   public InvalidNumericConvertException(String message, Throwable cause)
   {
     super(message, cause);
   }
 
   public InvalidNumericConvertException(Throwable cause)
   {
     super(cause);
   }
 }