Race conditions in concurrent access to a file
==============================================

Suppose we want to implement a file manipulation system such that
  * one thread, Editor, periodically changes the file contents and then saves the file
  * another thread, AutoSave, periodically saves the file.

To this end, we construct a File class with 
  * boolean field "changed" 
  * string field "contents"
      
together with methods 
* change(), which
    * sets "changed" to true
    * alters in-memory value of "contents" field
* save(), which
    * sets "changed" to false
    * writes file to disk.
The value of the "changed" field is supposed to record whether the in-memory file contents differs from the data written to disk.  Since writing to disk is expensive, we wish to execute this only when necessary.  To this end, we wish to define save() so that it satisfies this property:

(A) call writeToDisk() only when the value of "changed" is true.

To this end, we define save() as follows: 

~~~
if (changed) {
    writeToDisk();
}
~~~
   
Without synchronization, this approach fails to ensure the desired property (A).  For example, consider a situation where after changing the file contents, Editor saves the file.  Given File's implementation of the change and save procedures, this initiates a sequence of steps as follows:
1. load the value of "changed" onto the operand stack; since the file was just changed, this value is 0 (true)
2. pop the operand stack, compare the result with 0 (true) and accordingly (in this case) go to an instruction corresponding to the case where the compared values are equal
3. push the value 1 (false) onto the stack
4. pop the stack and store the result in the "changed" value

After completing all steps 1-4, the Editor thread then calls writeToDisk().

Now, recall that the AutoSave process also has access to the File object and can set the "changed" variable.  Specifically, it can of course happen that, at some point after step 1 (loading the "changed" variable) but before the file is written to disk, AutoSave acts to call save().  This, too, initiates a sequences of steps 1-4.  Since the two threads are asynchronous, AutoSave's save process may get entirely completed before Editor's save process calls writeToDisk().  Assuming no other processes modify the "changed" variable, it will then happen that Editor calls writeToDisk() while the "changed" variable is set to False, and this violates desired property (A).

To be a bit more explicit, let me write e1,e2,..., a1,a2.,,, for the steps 1-4 of File's save() method as instantiated respectively in the Editor and Autosave threads.  Then, the following sequence would be a race condition admitted by the naive implementation described above:

a1, e1, e2, a2, e3, e4, a3, a4.


