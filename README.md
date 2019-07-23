TDD Demo
========

A little project that demonstrates a use of TDD. 

Here we implement a "calculator emulator".  In other words, we don't have an API for `add()`, `subtract()` etc as you
might in traditional introductions to TDD, but instead simply have an API for `press()`, emulating the user pressing
a button, and `display()` to see what's on the display. Implementing it this way brings an extra level of complexity
that I think really drives home the value of TDD.  There are some tricky edge cases to be dealt with - for example,
if I press `4` `+` `=`, what does that display?  (try it on your desktop calculator!  You might be surprised). 

I suggest looking through the commit history, I've tried to stick quite strictly to TDD, only ever writing as much
test as needed to write prod code, and as much prod code to make the test pass.  I've also added comments where I've
had particular challenges or insights. 

The implementation is not fully complete.  For a start, you may think of other tests you could write around the existing
code.  Do they pass as you expect?  Try them on a normal calculator and see if you get the same answers.  

If you want to try things for yourself, there's a few other features you could add:

* The current code only deals with integers.  Can you make it work with decimals?
* Implement a `+/-` button for entering negative numbers
* Implement `M`, `M+` and `M-` for memory features
* The C button only clears the last number entered.  Implement an `AC` button to clear the whole calculation.
* Write a simple command line UI for pressing buttons
