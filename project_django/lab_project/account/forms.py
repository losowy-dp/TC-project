from django import forms
from django.template import Template
from django.utils.safestring import mark_safe

from .models import *


class LoginForm(forms.Form):
    username = forms.CharField(widget=forms.TextInput)
    password = forms.CharField(widget=forms.PasswordInput)

    def __init__(self, *args, **kwargs):
        super(LoginForm, self).__init__(*args, **kwargs)
        self.fields['username'].widget = forms.TextInput(attrs={
            'id': 'username_login',
            'placeholder': 'Imie',
            'value': "",
        })
        self.fields['password'].widget = forms.PasswordInput(attrs={
            'id': 'password_login',
            'placeholder': 'Hasło',
        })

class RegistrationsForm(forms.ModelForm):
    # username = forms.CharField(widget=forms.TextInput(attrs={'placeholder': 'Logo'}))
    # first_name = forms.CharField(widget=forms.TextInput(attrs={'placeholder': 'Imie'}))
    password = forms.CharField(label='Hasło', widget=forms.PasswordInput, min_length=6)
    password2 = forms.CharField(label='Powtorz hasło', widget=forms.PasswordInput(attrs={'placeholder': 'Potwierdz hasło'}))

    class Meta:
        model = User
        fields = ('username', 'first_name', 'email',)

    def __init__(self, *args, **kwargs):
        super(RegistrationsForm, self).__init__(*args, **kwargs)
        self.fields['username'].widget = forms.TextInput(attrs={
            'id': 'usernamefield',
            # 'required': "",
            'value': "",
            # 'minlength': "4",
        })
        self.fields['email'].widget = forms.EmailInput(attrs={
            'id': 'email',
            # 'pattern': "^.+@.+/..+$",
            'placeholder': 'Email',
        })
        self.fields['password'].widget = forms.PasswordInput(attrs={
            'id': 'password1',
        })
        self.fields['password2'].widget = forms.PasswordInput(attrs={
            'id': 'password2',
        })
        for key, field in self.fields.items():
            if isinstance(field.widget, forms.TextInput) or \
                isinstance(field.widget, forms.PasswordInput):
                field.widget.attrs.update({'placeholder': field.label})

    def clean_password2(self):
        cd = self.cleaned_data
        if cd['password'] != cd['password2']:
            raise forms.ValidationError('Hasla sa rozne', code='password_error')
        return cd['password2']

    def clean_email(self):
        cd = self.cleaned_data
        if User.objects.filter(email=cd['email']):
            raise forms.ValidationError('Urzytkownik z takim email juz istnieje', code='email_error')
        return cd['email']


class CheckEmailForm(forms.Form):
    email = forms.EmailField(label='Email', widget=forms.EmailInput)

    def clean_email(self):
        cd = self.cleaned_data
        if User.objects.filter(email=cd['email']):
            return cd['email']
        raise forms.ValidationError('Urzytkownika z takim email nie istnieje.', code='email_error')


class ChangePassword(forms.Form):
    old_password = forms.CharField(label='Stare haslo', widget=forms.PasswordInput(attrs={
        'id': 'old_password',
    }))
    new_password1 = forms.CharField(label='Nowe haslo', widget=forms.PasswordInput(attrs={
        'id': 'new_password',
    }))
    new_password2 = forms.CharField(label='Powtorz haslo', widget=forms.PasswordInput)

    def clean_new_password2(self):
        cd = self.cleaned_data
        if cd['old_password'] == cd['new_password1']:
            raise forms.ValidationError('Nowe hasło takie same jak stare', code='password_error')
        if cd['new_password1'] != cd['new_password2']:
            raise forms.ValidationError('Hasla sa rozne', code='password_error')
        return cd['new_password2']


class ProfileForm(forms.ModelForm):
    photo = forms.ImageField(label="Zdjęcie")

    class Meta:
        model = Profile
        fields = ('photo',)


    def __init__(self, *args, **kwargs):
        super(ProfileForm, self).__init__(*args, **kwargs)
        self.fields['photo'].widget = forms.FileInput(attrs={
            'id': 'photo',
            'style': 'display:none'
            # 'required': "",
            # 'value': "",
            # 'minlength': "4",
        })


class EditUserForm(forms.Form):
    first_name = forms.CharField(label='Imie', widget=forms.TextInput)
    last_name = forms.CharField(label='Nazwisko', widget=forms.TextInput)
    birthday = forms.DateField(label='Dzien narodzenia', widget=forms.DateInput)
    photo = forms.ImageField()

    def __init__(self, *args, **kwargs):
        super(EditUserForm, self).__init__(*args, **kwargs)
        self.fields['first_name'].widget = forms.TextInput(attrs={
            'id': 'first_name',
        })
        self.fields['last_name'].widget = forms.TextInput(attrs={
            'id': 'last_name',
        })
        self.fields['birthday'].widget = forms.TextInput(attrs={
            'type': 'date',
            'id': 'birthday'
            # 'required': "",
            # 'value': "",
            # 'minlength': "4",
        })


class EditNumberOfPhone(forms.Form):
    number_of_phone = forms.CharField(label='Imie', widget=forms.TextInput)

    def __init__(self, *args, **kwargs):
        super(EditNumberOfPhone, self).__init__(*args, **kwargs)
        self.fields['number_of_phone'].widget = forms.TextInput(attrs={
            'id': 'phone',
            'type': 'tel',
            'class': 'form-control',
        })
