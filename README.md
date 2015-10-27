#PinDialog
Dialog for entering vary-length digital pin code. Android, API 8 compatible.

##Attention Message
There is no any pin-protection mechanism. If you need it look at https://github.com/OrangeGangsters/LolliPin.

##Usage
```
...
        //callback to specify action to do with pin-code entered by user
        OnPinEnteredListener onPinCodeEnteredListener = new OnPinEnteredListener() {
            @Override
            public void onPinEntered(String pin) {showShortToastWithText(pin);}
        };

        //instantiating and showing full-screen dialog with pin-pad
        Dialog d = new DecimalPinDialog(this, 3, onPinCodeEnteredListener);
        d.show();
    }

    private void showShortToastWithText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
```
Look at the sources to know more.

##Development Plan

1. add 'Backspace' button to bottom-right corner

1. add 'Clear all' button  to bottom-left corner

1. add Dialog wrapper with methods for [pin code creation, pin checking, pin changing] workflows

##Origin
https://github.com/AmniX/PinLockDialog

##Licence
The MIT License (MIT)

Vlad Zamskoi © 2015 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.