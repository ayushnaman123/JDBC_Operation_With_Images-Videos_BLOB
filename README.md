To Store, and Retrieve the images, Videos fils which may be jpg, png, mp4 etc. 
We have a concept call BLOB.
BLOB - Binary Large Objects.
We have to used the Method here at the time of insertion through the prepared statement object the method name is setBinaryStream(int pos, InputFileStream);
And for Retrieval We have to use method getBinaryStream(int pos) which return InputStream.
