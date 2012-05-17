<div id="starterPage">
    <label for="accessToken">access token</label>
    <input id="accessToken" type="text" style="width: 500px">
    <a href="" class="fetchFriends">Fetch Friends</a>

    <div class="friends"></div>
    <script type="text/javascript">
        $(function () {
            $("#starterPage").delegate("a.fetchFriends", "click", function () {
                var token = $("#accessToken").val();
                if (/^\s*$/g.test(token)) {
                    alert("please set token first");
                } else {
                    $.ajax({
                        type : "get",
                        url : contextPath + "/friends.json",
                        data : {token : token},
                        dataType : "json"
                    }).done(function (val) {
                         $(".friends").html($("#tmpl-FriendsView").render({users:val}));
                     });
                }
                return false;
            });
        });
    </script>
</div>

<script id="tmpl-FriendsView" type="text/html">
    <ul>
        <li>
            {{for users}}
              {{:name}}
            {{/for}}
        </li>
    </ul>
</script>