#PinDialog
Dialog for entering vary-length digital pin code. Android, API 8 compatible.

##Attention Message
There is no any pin-protection mechanism. If you need it look at https://github.com/OrangeGangsters/LolliPin.

##Usage

###Create Pin
```
int pinLength = 5;
final PinScreen pinCreation = new PinScreen(this, pinLength);
pinCreation.setOnPinConfirmationFailsListener(new OnPinConfirmationFailsListener() {
   @Override
   public void pinConfirmationFailed() {
       //do smth here
       //e.g.
       Toast.makeText(MainActivity.this, "Sorry, Pins don't match", Toast.LENGTH_SHORT).show();
       
       //e.g.
       pinCreation.startPinCreation();
   }
});
pinCreation.setOnPinCreatedListener(new OnPinCreatedListener() {
   @Override
   public void pinCodeCreated(String pinCode) {
       //do smth here
       //e.g.
       Toast.makeText(MainActivity.this, "Good job: " + pinCode, Toast.LENGTH_SHORT).show();
   }
});
pinCreation.startPinCreation();
```

###Request Pin
```
int pinLength = 3;
final PinScreen pinRequest = new PinScreen(this, pinLength);
pinRequest.setOnPinReceivedListener(new OnPinReceivedListener() {
   @Override
   public void pinCodeReceived(String pinCode) {
       //do smth here
       //e.g.
       Toast.makeText(MainActivity.this, "Entered pin is: " + pinCode, Toast.LENGTH_SHORT).show();
   }
});
pinRequest.startPinRequest();
```
##Development Plan
1. change indicator stroke color to grey (the same as pinpad button stroke)

1. add resources (dimensions) for small screen

##Origin
https://github.com/AmniX/PinLockDialog

##Licence
The MIT License (MIT)

Vlad Zamskoi © 2015 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.