<%--
  Simplified, user-friendly login page
  - No gradients
  - Stable, responsive layout
  - Floating labels managed by JS (handles autofill)
  - Password visibility toggle
  - Clear error display preserved
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign in — LearningLog</title>
    <style>
        :root{--bg:#eef2f7;--card:#ffffff;--accent:#2b6cb0;--muted:#6b7280;--error-bg:#fff0f0;--error-border:#ffb6b6}
        *{box-sizing:border-box}
        html,body{height:100%;margin:0;font-family:Arial,Helvetica,sans-serif;background:var(--bg);color:#0b1220}

        .wrap{min-height:100%;display:flex;align-items:center;justify-content:center;padding:40px}
        .card{width:400px;max-width:96%;background:var(--card);border-radius:12px;padding:28px;box-shadow:0 10px 30px rgba(16,24,40,0.08)}
        .brand{display:flex;gap:12px;align-items:center;margin-bottom:12px}
        .logo{width:44px;height:44px;border-radius:8px;background:var(--accent);display:flex;align-items:center;justify-content:center;color:white;font-weight:700}
        h1{margin:0;font-size:20px}
        p.lead{margin:6px 0 16px;color:var(--muted);font-size:13px}

        form{display:flex;flex-direction:column;gap:12px}
        .field{position:relative}
        input[type="email"],input[type="password"]{width:100%;padding:14px;border-radius:8px;border:1px solid #d6dce6;background:transparent;font-size:14px}
        input:focus{outline:none;border-color:var(--accent);box-shadow:0 6px 18px rgba(43,108,176,0.08)}

        /* floating label */
        label.floating{position:absolute;left:12px;top:12px;color:var(--muted);pointer-events:none;transition:all .12s ease;background:transparent;padding:0 6px}
        input.filled + label.floating,input:focus + label.floating{transform:translateY(-130%);font-size:12px;color:var(--accent)}

        .password-toggle{position:absolute;right:8px;top:8px;border:none;background:transparent;padding:6px;cursor:pointer;font-size:13px;color:var(--muted)}

        .controls{display:flex;align-items:center;justify-content:space-between;margin-top:4px}
        .controls label{font-size:13px;color:var(--muted);display:flex;align-items:center;gap:8px}

        .btn{width:100%;padding:12px;border-radius:8px;border:0;background:var(--accent);color:white;font-weight:600;cursor:pointer}
        .btn:active{transform:translateY(1px)}

        .helper{margin-top:8px;text-align:center;color:var(--muted);font-size:13px}

        .error{background:var(--error-bg);border:1px solid var(--error-border);color:#7b1111;padding:10px;border-radius:8px;font-size:13px;display:flex;gap:10px;align-items:center}

        @media (max-width:420px){.card{padding:18px}}
    </style>
</head>
<body>
    <div class="wrap">
        <div class="card" role="main" aria-labelledby="signin-heading">
            <div class="brand">
                <div class="logo">LL</div>
                <div>
                    <h1 id="signin-heading">Welcome back</h1>
                    <p class="lead">Sign in to continue to LearningLog</p>
                </div>
            </div>

            <% String errorMsg = (String) request.getAttribute("error");
               if (errorMsg==null) { errorMsg=""; }
            %>
            <% if (!errorMsg.isEmpty()) { %>
                <div class="error" role="alert">
                    <div style="font-weight:700">Error</div>
                    <div style="opacity:.95;font-size:13px;margin-left:6px"><%= errorMsg %></div>
                </div>
            <% } %>

            <form action="login" method="post" novalidate onsubmit="return submitForm(event)">
                <div class="field">
                    <input id="email" name="email" type="email" required aria-required="true">
                    <label for="email" class="floating">Email address</label>
                </div>

                <div class="field" style="margin-top:6px">
                    <input id="password" name="password" type="password" required aria-required="true">
                    <label for="password" class="floating">Password</label>
                    <button type="button" class="password-toggle" aria-label="Toggle password visibility" onclick="togglePassword()">Show</button>
                </div>

                <div class="controls">
                    <label><input type="checkbox" name="remember"> Remember me</label>
                    <a href="#" style="font-size:13px;color:var(--muted);text-decoration:none">Forgot?</a>
                </div>

                <div style="margin-top:8px"><button class="btn" type="submit">Sign in</button></div>

                <div class="helper">Need an account? <a href="register" style="color:var(--accent);text-decoration:none;font-weight:600">Create one</a></div>
            </form>
        </div>
    </div>

    <script>
        // Manage floating labels reliably (handles autofill and programmatic value changes)
        function updateFilled(input){
            if(input.value && input.value.trim() !== '') input.classList.add('filled'); else input.classList.remove('filled');
        }

        document.addEventListener('DOMContentLoaded', function(){
            document.querySelectorAll('input[type="email"],input[type="password"]').forEach(function(i){
                updateFilled(i);
                i.addEventListener('input', function(){ updateFilled(i); });
            });
        });

        function togglePassword(){
            var pw = document.getElementById('password');
            var btn = document.querySelector('.password-toggle');
            if(pw.type === 'password'){ pw.type = 'text'; btn.textContent = 'Hide'; }
            else { pw.type = 'password'; btn.textContent = 'Show'; }
            updateFilled(pw);
        }

        function submitForm(e){
            var em=document.getElementById('email');
            var pw=document.getElementById('password');
            if(!em.checkValidity() || !pw.checkValidity()){
                // show simple shake for feedback
                var c=document.querySelector('.card');
                c.animate([{transform:'translateX(0)'},{transform:'translateX(-6px)'},{transform:'translateX(6px)'},{transform:'translateX(0)'}],{duration:260,iterations:1});
                em.reportValidity();
                return false;
            }
            return true; // proceed with submission to server
        }
    </script>
</body>
</html>