from django import forms
from crispy_forms.helper import FormHelper
from crispy_forms.layout import Layout, Submit, Row, Column
from django.forms import Textarea

from .models import *

from django import forms
from crispy_forms.helper import FormHelper
from crispy_forms.layout import Layout, Submit, Row, Column

STATES = (
    ('', 'Choose...'),
    ('MG', 'Minas Gerais'),
    ('SP', 'Sao Paulo'),
    ('RJ', 'Rio de Janeiro')
)


class AddressForm(forms.Form):
    email = forms.CharField(widget=forms.TextInput(attrs={'placeholder': 'Email'}))
    password = forms.CharField(widget=forms.PasswordInput())
    address_1 = forms.CharField(
        label='Address',
        widget=forms.TextInput(attrs={'placeholder': '1234 Main St'})
    )
    address_2 = forms.CharField(
        widget=forms.TextInput(attrs={'placeholder': 'Apartment, studio, or floor'})
    )
    city = forms.CharField()
    state = forms.ChoiceField(choices=STATES)
    zip_code = forms.CharField(label='Zip')
    check_me_out = forms.BooleanField(required=False)


class TransportationForm(forms.ModelForm):
    # start_location = forms.CharField(max_length=1000)
    # delivery_locatio = forms.CharField(max_length=1000)
    # price = forms.FloatField()
    # currency = forms.CharField(max_length=3)
    # photo = forms.ImageField(='photo')

    class Meta:
        model = Transportation
        fields = ['start_location', 'delivery_location', 'price', 'currency', 'model', 'typeCar', 'description', 'data_start_deliveri', 'data_start_shipment', 'photo', 'punktA_1', 'punktA_2', 'punktB_1', 'punktB_2']
        widgets = {
            'start_location': forms.TextInput(attrs={'value':'Lublin', 'id': 'start_loc'}),
           # 'photo': forms.FileInput(attrs={'id': 'file_input'}),
            'delivery_location': forms.TextInput(attrs={'value': 'Warszawa', 'id': 'delivery_loc'}),
            'description': Textarea(attrs={'cols': 80, 'rows': 8}),
            'data_start_deliveri': forms.DateInput(format=('%m/%d/%Y'), attrs={'class':'form-control', 'placeholder':'Select a date', 'type':'date'}),
            'data_start_shipment': forms.DateInput(format=('%m/%d/%Y'),
                                                   attrs={'class': 'form-control', 'placeholder': 'Select a date',
                                                          'type': 'date'}),
            'photo': forms.FileInput(attrs={'id': 'photo', 'style': 'display:none',}),
            'punktA_1': forms.TextInput(attrs={'id': 'punktA_1', 'style': 'display:none', }),
            'punktA_2': forms.TextInput(attrs={'id': 'punktA_2', 'style': 'display:none', }),
            'punktB_1': forms.TextInput(attrs={'id': 'punktB_1', 'style': 'display:none', }),
            'punktB_2': forms.TextInput(attrs={'id': 'punktB_2', 'style': 'display:none', }),
        }

        def __init__(self, *args, **kwargs):
            super(TransportationForm, self).__init__(*args, **kwargs)
            self.fields['start_location'].widget = forms.TextInput(attrs={
                'id': 'search1',
            })
            # self.fields['photo'].widget = forms.FileInput(attrs={
            #     'id': 'photo',
            #     'style': 'display:none'
            # })


class AddTransportations(forms.ModelForm):

    class Meta:
        model = Transportation
        fields = '__all__'


class FindTransportForm(forms.Form):
    start = forms.CharField(max_length=1000)
    end = forms.CharField(max_length=1000)
