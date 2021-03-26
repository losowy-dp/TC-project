from django.shortcuts import render
from .forms import LoginForm, RegistrationsForm
from django.contrib.auth import authenticate, login, logout
from django.http import HttpResponse


def index(request):
    return render(request, 'accounts/index.html')


def user_login(request):
    if request.method == 'POST':
        form = LoginForm(request.POST)
        if form.is_valid():
            cd = form.cleaned_data
            user = authenticate(username=cd['username'], password=cd['password'])
            if user is not None:
                if user.is_active:
                    login(request, user)
                    return render(request, 'transportation_car/index.html', {'user': user})
                    # return HttpResponse('Authenticated successfully')
                else:
                    # return render(request, 'accounts/disabled_account.html', {'error': 'Disabled account'})
                    return HttpResponse('Disabled account')
            else:
                return render(request, 'accounts/invalid_data.html', {'error': 'Invalid data!!!'})
                # return HttpResponse('Invalid login')
    else:
        form = LoginForm()
    return render(request, 'accounts/login.html', {'form': form})


def user_logout(request):
    logout(request)
    return render(request, 'accounts/index.html')


def register(request):
    if request.method == 'POST':
        user_form = RegistrationsForm(request.POST)
        if user_form.is_valid():
            # Create a new user object but avoid saving it yet
            new_user = user_form.save(commit=False)
            # Set the chosen password
            new_user.set_password(user_form.cleaned_data['password'])
            # Save the User object
            new_user.save()
            return render(request, 'accounts/register_done.html', {'new_user': new_user})
        else:
            return render(request, 'accounts/invalid_data.html', {'error': 'Invalid data!!!'})
    else:
        user_form = RegistrationsForm()
    return render(request, 'accounts/register.html', {'user_form': user_form})
