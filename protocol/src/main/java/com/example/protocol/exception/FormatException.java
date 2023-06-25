 package com.example.protocol.exception;
 
 public class FormatException extends RuntimeException
 {
   private static final long serialVersionUID = -7199411798946846071L;
 
   public FormatException()
   {
   }
 
   public FormatException(String message)
   {
     super(message);
   }
 
   public FormatException(String message, Throwable cause)
   {
     super(message, cause);
   }
 
   public FormatException(Throwable cause)
   {
     super(cause);
   }
 }