

//uniform sampler2D u_texure;

varying vec2 v_texCoords;

void main(){
    vec4 pos=gl_FragCoord;

    gl_FragColor=vec4(pos.x/800,pos.y/600,0,1);
}