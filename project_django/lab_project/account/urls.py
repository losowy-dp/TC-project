from django.urls import path
from django.contrib.auth import views as auth_views
from .views import *


urlpatterns = [
    path('', index, name='choose_register'),
    path('login', user_login, name='login'),
    path('register', user_login, name='register'),
    path('logout/', user_logout, name='logout'),
    path('user/<int:id>', getUser.as_view()),
    # path('profile/<int:user_id>', getProfile.as_view()),
    path('profile/edit/<int:user_id>', editProfile),
    # path('login_app/', User_login_app.as_view(), name='user_login_app'),
    path('reset_password/done/', auth_views.PasswordResetDoneView.as_view(template_name='account/reset_password/password_reset_done.html'), name='password_reset_done'),
    path('reset/<uidb64>/<token>/', auth_views.PasswordResetConfirmView.as_view(template_name="account/reset_password/password_reset_confirm.html"), name='password_reset_confirm'),
    path('reset/done/', auth_views.PasswordResetCompleteView.as_view(template_name='account/reset_password/password_reset_complete.html'), name='password_reset_complete'),
    path('reset_password/', reset_password, name="password_reset"),
    path('edit_profile/', editPr, name="edit_profile"),
    path('users/', UserList.as_view()),
    path('users/<int:pk>/', UserDetail.as_view()),
    path('my_profile/<int:user_id>', userProfile, name="profile"),
    path('edit_profile/<int:user_id>', editUserProfile, name="edit_profile"),
    path('change_password/<int:user_id>', changePassword, name="change_password"),
    path('edit_data_profile/<int:user_id>', editDataProfile, name="edit_data_profile"),
    path('edit_phone/<int:user_id>', editNumberOfPhone, name="edit_phone"),
    path('edit_photo/<int:user_id>', editPhoto, name="edit_photo"),
    path('userDetail/<int:pk>/', UserAPI.as_view()),
    path('profileDetail/<int:pk>/', ProfileAPI.as_view()),

    # path('edit_kastyl/<int:user_id>/', kostyl),
]
