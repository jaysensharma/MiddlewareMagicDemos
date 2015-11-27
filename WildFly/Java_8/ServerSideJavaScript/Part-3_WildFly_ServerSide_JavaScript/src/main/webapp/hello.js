$undertow.onGet("/hello",
                  { headers: {"content-type": "text/plain"}},
                      [function ($exchange) {
                         return "Hello, MiddlewareMagic!!!";
                   }])